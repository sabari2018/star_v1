package fr.istic.mob.starv1

import android.os.Bundle
import android.os.Environment
import android.system.Os.mkdir
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {

  //  val data_source:String ="https://data.explore.star.fr/explore/dataset/tco-busmetro-horaires-gtfs-versions-td/download/?format=json&timezone=Europe/Berlin&lang=fr"
    var permission:Permission = Permission(this)

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
                Toast.makeText(this@MainActivity, "ICI C'EST PARIS", Toast.LENGTH_SHORT).show()
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
            3L,
            TimeUnit.SECONDS
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
                        Toast.makeText(this@MainActivity, "Telechargement termie !", Toast.LENGTH_SHORT).show()
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
                     //var js:JSONObject = response!!.optJSONObject(0).getJSONObject("fields")
                    // var url:String = js.getString("url")
                     var obj = JSONObject()
                     for (i in 0 until record!!.length()){
                          obj = record.getJSONObject(i).getJSONObject("fields")
                         if (checkNewFileByDate(obj.get("debutvalidite").toString(), obj.get("finvalidite").toString())){
                             Log.e("LE BON FICHIER", obj.getString("id"))
                             break
                         }
                     }
                     Log.e("SAD", obj.get("id").toString())
                    // unzipFileFromUrl(url)

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
                     var name:String = url.substring(url.lastIndexOf("/")+1,url.length)
                     var folder:String ="starv1/"
                     var dirFile:File = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),folder)
                     if(!dirFile.exists()){
                         mkdir(dirFile.absolutePath,0)
                     }

                     var file = File(dirFile,name)
                     var stream = FileOutputStream(file)
                     stream.write(binaryData)
                     stream.close()

                     /*var exportPath:String = file.absolutePath
                     exportPath = exportPath.replace(".zip","")
                     exportPath = "$exportPath/"

                     var dzip = Decompress(file.absolutePath, exportPath)
                     dzip.unzip()*/


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
}