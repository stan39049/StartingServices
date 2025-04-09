package edu.temple.startingservices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*

class Service : Service() {

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Default + serviceJob)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent?.getIntExtra("countdown_time", 0) ?: 0

        if (time > 0) {
            serviceScope.launch {
                for (i in time downTo 0) {
                    Log.d("Service", "Countdown: $i")
                    delay(1000)
                }
                stopSelf()
            }
        } else {
            Log.d("Service", "Invalid Input")
            stopSelf()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d("Service", "Service destroyed")
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}