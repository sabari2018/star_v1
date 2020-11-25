package fr.istic.mob.starv1

import android.util.Log
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

/**
 * Created by matok on 21/11/2017.
 */
class Decompress(private val _zipFile: String, private val _location: String) {
    fun unzip(): Boolean {
        try {
            val fin = FileInputStream(_zipFile)
            val zin = ZipInputStream(fin)
            var ze: ZipEntry? = null
            while (zin.nextEntry.also { ze = it } != null) {
                Log.v("XXXXDecompress", "Unzipping " + ze!!.name)
                if (ze!!.isDirectory) {
                    _dirChecker(ze!!.name)
                } else {
                    val fout =
                        FileOutputStream(_location + ze!!.name)
                    val bufout = BufferedOutputStream(fout)
                    val buffer = ByteArray(1024)
                    var read = 0
                    while (zin.read(buffer).also { read = it } != -1) {
                        bufout.write(buffer, 0, read)
                    }
                    bufout.close()
                    zin.closeEntry()
                    fout.close()
                }
            }
            zin.close()
            Log.d("XXXXUnzip", "Dezipping complete. path :  $_location")
            return true
        } catch (e: Exception) {
            Log.e("XXXXDecompress", "dezip", e)
            Log.d("XXXXUnzip", "Dezipping failed")
        }
        return false
    }

    private fun _dirChecker(dir: String) {
        val f = File(_location + dir)
        if (!f.isDirectory) {
            f.mkdirs()
        }
    }

    init {
        _dirChecker("")
    }
}