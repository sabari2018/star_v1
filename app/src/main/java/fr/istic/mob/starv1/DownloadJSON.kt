package fr.istic.mob.starv1

import android.content.Context
import android.os.Environment
import android.system.Os.mkdir
import android.util.Log
import androidx.room.Room
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.BinaryHttpResponseHandler
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import fr.istic.mob.starv1.dbConfig.StarDatabase
import fr.istic.mob.starv1.entity.*
import fr.istic.mob.starv1.utility.URL
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*

class DownloadJSON(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {
    var exportPath:String =""
    private val filesNames = arrayOf("trips.txt","stops.txt","routes.txt","calendar.txt","stop_times.txt")

    override fun doWork(): Result {

        try {
            //Ici mon telechargement
            loadData()



        }catch (e:Exception){
            return Result.retry()
        }

        return Result.success()
    }


     fun loadData(){ //W
        var client: AsyncHttpClient = AsyncHttpClient()
         client[URL, object : JsonHttpResponseHandler() {
             override fun onSuccess(statusCode: Int, headers: Array<out Header>?, response: JSONArray?) {
                 try {
                     //var record:JSONArray = response.getJSONArray("fields")
                     var js: JSONObject = response!!.optJSONObject(0).getJSONObject("fields")
                     var url:String = js.getString("url")

                     unzipFileFromUrl(url)

                 }catch (e: JSONException){e.printStackTrace()}
             }

             override fun getUseSynchronousMode(): Boolean {
                 return false
             }

             override fun setUseSynchronousMode(useSynchronousMode: Boolean) {}
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
                // showDialog(0)
             }

             override fun onSuccess(statusCode: Int, headers: Array<out Header>?, binaryData: ByteArray?) {
                 try {
                     var name:String = url.substring(url.lastIndexOf("/")+1,url.length)
                     var folder:String ="starv1/"
                     var dirFile:File = File(applicationContext.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),folder)
                     if(!dirFile.exists()){
                         mkdir(dirFile.absolutePath,0)
                     }

                     var file:File = File(dirFile,name)
                     var stream: FileOutputStream = FileOutputStream(file)
                     stream.write(binaryData)
                     stream.close()

                     exportPath = file.absolutePath
                     exportPath = exportPath.replace(".zip","")
                     exportPath = "$exportPath/"

                     var dzip:Decompress = Decompress(file.absolutePath, exportPath)
                     dzip.unzip()

                     //Charge la bd
                     loadFilesToDatabase(exportPath)


                 }catch (e: IOException){e.printStackTrace()}
             }

             override fun onProgress(bytesWritten: Long, totalSize: Long) {
                 super.onProgress(bytesWritten, totalSize)

                 val `val` = (bytesWritten * 100 / totalSize).toInt()
                 Log.d("STARX", "downloading ..... $`val`")
                // progressD.setProgress(`val`)
                // progressD.getCurrentFocus()
             }
             override fun onFailure(statusCode: Int, headers: Array<out Header>?, binaryData: ByteArray?, error: Throwable?) {
                 Log.d("STARX", "downloading ..... $error")
             }

             override fun onFinish() {
                 super.onFinish()
             }

             override fun getUseSynchronousMode(): Boolean {
                 return false
             }

             override fun setUseSynchronousMode(useSynchronousMode: Boolean) {}
         })
     }

    fun loadFilesToDatabase(storageDir:String){

        //Trip
        var file = File(storageDir,filesNames[0])
         var trip:Trip

        var bReader = BufferedReader(FileReader(file))

        val lines = bReader.readLines()

        val db = Room.databaseBuilder(
            applicationContext,
            StarDatabase::class.java,"star_db"
        )   .fallbackToDestructiveMigration()
            .build()

        val tripDao = db.tripDao()
/*
        lines.forEach { line ->
            val values = line.split(",")

            trip = Trip(values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8],values[9])

            tripDao.insert(trip)
        }*/

        //Stops
       /* val file1 = File(storageDir,filesNames[1]);
        var stop:Stop
        val bReader1 = BufferedReader(FileReader(file1))
        val stopLines = bReader1.readLines()
        val stopDao = db.stopDao()

        stopLines.forEach { line ->
            val values = line.split(",")
            stop = Stop(values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8],values[9],values[10],values[11])
            stopDao.insert(stop)
        }*/

        //Routes
        val file2 = File(storageDir,filesNames[2])
        var route:BusRoute
        val bReader2 = BufferedReader(FileReader(file2))
        val routeLines = bReader2.readLines()
        val routeDao = db.busRouteDao()

        routeLines.forEach { line ->
            val values = line.split(",")
            route = BusRoute(values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8],values[9])
            routeDao.insert(route)
        }
        //Calendar
        file = File(storageDir,filesNames[3])
        var calender:Calender
        bReader = BufferedReader(FileReader(file))
        val calenderLines = bReader.readLines()
        val calenderDao = db.calenderDao()

        calenderLines.forEach { line ->
            val values = line.split(",")
            calender = Calender(values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8],values[9])
            calenderDao.insert(calender)
        }

        //stop_times
        file = File(storageDir,filesNames[4])
        var stopTimes:StopTime
        bReader = BufferedReader(FileReader(file))
        //val stopTimesLines = bReader.readLines()
        val stopTimesDao = db.stopTimeDao()

        try {
            bReader.readLines().forEach { line ->
                val values = line.split(",")
                stopTimes = StopTime(null, values[0],values[1],values[2],values[3],values[4],values[5],values[6],values[7],values[8])
                stopTimesDao.insert(stopTimes)
            }
        }catch (e: java.lang.Exception){
            Log.d("erreur", e.message.toString())
        }

    }
}