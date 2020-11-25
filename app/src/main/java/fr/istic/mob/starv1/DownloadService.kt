package fr.istic.mob.starv1

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast

private const val TAG = "DownloadService"
class DownloadService : IntentService("DownloadService ") {

    override fun onHandleIntent(intent: Intent?) {
        Log.e(TAG, "onHandleIntent: ")
    }
}