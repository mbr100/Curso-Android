package com.marioborrego.hellokotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener
{
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener{speak()}
    }

    @SuppressLint("SetTextI18n")
    private fun speak()
    {
        var message: String = findViewById<TextView>(R.id.etMessage).text.toString()

        if (message.isEmpty())
        {
            findViewById<TextView>(R.id.tvStatus).text = "Introduzca un texto"

            message="¿Es enserio? Pon algo ya en el Edit text"
        }

        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }


    @SuppressLint("SetTextI18n")
    override fun onInit(status: Int)
    {
        if (status == TextToSpeech.SUCCESS) {
            findViewById<TextView>(R.id.tvStatus).text = "Servicio Listo"
            tts!!.language = Locale("ES")
        } else {
            findViewById<TextView>(R.id.tvStatus).text = "No disponible :("
        }
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}