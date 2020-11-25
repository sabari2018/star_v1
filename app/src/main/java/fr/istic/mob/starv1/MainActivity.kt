package fr.istic.mob.starv1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.system.Os.mkdir
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.BinaryHttpResponseHandler
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import fr.istic.mob.starv1.dbConfig.StarDatabase
import fr.istic.mob.starv1.utility.Permission
import fr.istic.mob.starv1.utility.URL
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {

  //  val data_source:String ="https://data.explore.star.fr/explore/dataset/tco-busmetro-horaires-gtfs-versions-td/download/?format=json&timezone=Europe/Berlin&lang=fr"
    var permission:Permission = Permission(this)
    private val CHANNEL_ID = "Star_v1"
    private val notificationId = 10
    private val filesNames = arrayOf("trips.txt","stop_times.txt","stops.txt","routes.txt","calendar.txt")
    var exportPath:String =""

    val workManager = WorkManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //loadData()
        periodaticworkmanager.setOnClickListener(this@MainActivity)


    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.periodaticworkmanager -> {
               // showNotification(this)

                loadData()
                /*permission.requestPermission(listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    object : Permission.PermissionCallback {
                        override fun onGranted() {

                            StartPeriodicWorkManager()
                        }

                        override fun onDenied() {

                            Toast.makeText(this@MainActivity, "storage permission dinied", Toast.LENGTH_SHORT).show()
                            //show message if not allow storage permission
                        }

                    })*/
            }
        }
    }

    private fun StartPeriodicWorkManager() {
        loaderShow(true)
        val periodicWorkRequest = PeriodicWorkRequest.Builder(
            DownloadJSON::class.java,
            15L,
            TimeUnit.MINUTES
        ).setConstraints(
            Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        ).build()

        workManager.enqueue(periodicWorkRequest)


        workManager.getWorkInfoByIdLiveData(periodicWorkRequest.id)
            .observe(this@MainActivity, Observer {
                it?.let {
                    if (it.state == WorkInfo.State.ENQUEUED) {

                        loaderShow(false)
                        Toast.makeText(this@MainActivity, "Telechargement termine !", Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun loaderShow(b: Boolean) {
        when (b) {
            true -> llProgress.visibility = View.VISIBLE
            false -> llProgress.visibility = View.GONE
        }
    }

   /* override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISION_REQUEST)
            permission.onRequestPermissionsResult(grantResults)

    }*/
     fun loadData(){ //W
        var client:AsyncHttpClient = AsyncHttpClient()
         client[URL, object : JsonHttpResponseHandler() {
             override fun onSuccess(
                 statusCode: Int,
                 headers: Array<out Header>?,
                 response: JSONArray?
             ) {
                 try {
                     var record:JSONArray? = response
                     var js:JSONObject = response!!.optJSONObject(0).getJSONObject("fields")
                     var url:String = js.getString("url")

                     unzipFileFromUrl(url)

                 }catch (e:JSONException){e.printStackTrace()}
             }
         }]
     }
     fun unzipFileFromUrl(url:String){
         var client:AsyncHttpClient = AsyncHttpClient()

         val type = arrayOf(
             "application/octect-stream"
         )
         client.get(url, object : BinaryHttpResponseHandler(type) {

             override fun onStart() {
                 super.onStart()
                 loaderShow(true)
             }

             override fun onSuccess(statusCode: Int, headers: Array<out Header>?, binaryData: ByteArray?) {
                 try {
                     val name:String = url.substring(url.lastIndexOf("/")+1,url.length)
                     val folder ="starv1/"
                     val dirFile = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),folder)
                     if(!dirFile.exists()){
                         mkdir(dirFile.absolutePath,0)
                     }

                     val file = File(dirFile,name)
                     val stream = FileOutputStream(file)
                     stream.write(binaryData)
                     stream.close()

                     exportPath = file.absolutePath
                     exportPath = exportPath.replace(".zip","")
                     exportPath = "$exportPath/"

                     var dzip = Decompress(file.absolutePath, exportPath)
                     dzip.unzip()




                     filesToDatabase(exportPath)


                    // dismissDialog(0)
                    // progressD.dismiss()

                 }catch (e:IOException){e.printStackTrace()}
             }


             override fun onFailure(statusCode: Int, headers: Array<out Header>?, binaryData: ByteArray?, error: Throwable?) {
                 Log.d("STARX", "downloading ..... $error")
             }

             override fun onFinish() {
                 super.onFinish()
                 loaderShow(false)
             }
         })
     }

    fun checkNewFileByDate(from:String, to:String): Boolean{
       // var formatFrom = DateTimeFormatter.BASIC_ISO_DATE
        try {
            val date = LocalDate.now()

            val today = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            val resultat = today.equals(from)
            return resultat
        }catch (e:Exception) {
            e.printStackTrace()
            return false
        }

    }

    fun initDB(){
        val db = Room.databaseBuilder(
            applicationContext,
            StarDatabase::class.java,"star_db"
        ).build()


    }

    private fun showNotification(context: Context) {
        createNotificationChannel(context)
        val pendingIntent: PendingIntent = PendingIntent.getService(
            context, 0, Intent(context, DownloadService::class.java),
            Intent.FILL_IN_DATA
        )
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Star_v1")
            .setContentText("Click notification to download database")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder.build())
        }
    }

    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Star_v1"
            val descriptionText = "Click notification to download database"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun filesToDatabase(directory:String){

        val storageDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(storageDir,filesNames[0])

        val bReader = BufferedReader(FileReader(file))

        var line = bReader.readLine();
        while (line !=null){

        }


       /* val bufferedReader: BufferedReader = File("example.txt").bufferedReader()
        val inputString = bufferedReader.use { it.readText() }*/

        //"trips.txt","stop_times.txt","stops.txt","routes.txt","calendar.txt"

    }
}