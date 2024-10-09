package com.bewolf1121.androiddamster.activity1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bewolf1121.androiddamster.R

class primeraApp2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primera_app2)
        val txRespuesta = findViewById<TextView>(R.id.txRespuesta)
        val name:String = intent.extras?.getString("Hola_txRespuesta").orEmpty()
        txRespuesta.text ="Hola $name"



    }
}