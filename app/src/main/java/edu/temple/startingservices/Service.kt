package edu.temple.startingservices

import android.app.Service.START_NOT_STICKY
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Service {

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Default + serviceJob)

    fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val time = intent?.getIntExtra("countdown_time", 0) ?: 0

        if (time > 0) {
            serviceScope.launch {
                for (i in time downTo 0) {
                    Log.d("CountdownService", "Countdown: $i")
                    delay(1000)
                }
            }
        } else {
            Log.d("CountdownService", "Invalid countdown time received")
        }

        return START_NOT_STICKY
    }


}