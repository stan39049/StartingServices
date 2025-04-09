package edu.temple.startingservices

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editText = findViewById<EditText>(R.id.editText)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val secondsInput = editText.text.toString()
            if (secondsInput.isNotEmpty()) {
                val intent = Intent(this, Service::class.java).apply {
                    putExtra("countdown_time", secondsInput.toInt())
                }
                startService(intent)
            }
        }
    }
}