package fr.istic.mob.starv1

import android.content.Context
import android.os.Environment
import android.system.Os.mkdir
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.BinaryHttpResponseHandler
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import fr.istic.mob.starv1.utility.URL
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

class DownloadJSON(context: Context, workerParams: WorkerParameters): Worker(context, workerParams) {
    override fun doWork(): Result {

        try {
            //Ici mon telechargement
            for (i in 0..10){
                Log.d("aaaa*** ->","$i")
            }
           // loadData()
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
                     Log.e("SAD", response.toString())
                    // unzipFileFromUrl(url)

                 }catch (e: JSONException){e.printStackTrace()}
             }

             override fun getUseSynchronousMode(): Boolean {
                 return true
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

                     var exportPath:String = file.absolutePath
                     exportPath = exportPath.replace(".zip","")
                     exportPath = "$exportPath/"

                     var dzip:Decompress = Decompress(file.absolutePath, exportPath)
                     dzip.unzip()

                     Log.d("STARX", "******************************************************************")

                    // dismissDialog(0)
                    // progressD.dismiss()

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
         })
     }
}