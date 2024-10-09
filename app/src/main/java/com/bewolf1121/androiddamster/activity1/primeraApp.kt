package com.bewolf1121.androiddamster.activity1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.bewolf1121.androiddamster.R

class primeraApp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primera_app)
        val btnHola = findViewById<AppCompatButton>(R.id.bntHola)
        val txUser = findViewById<AppCompatEditText>(R.id.txtUser)

         btnHola.setOnClickListener {
            val nameResult = txUser.text.toString()

            if (nameResult.isNotEmpty()) {
                val intent = Intent(this, primeraApp2::class.java)
                intent.putExtra("Hola_txRespuesta", nameResult  )
                startActivity(intent)

            }
        }
    }
}