package com.bewolf1121.androiddamster.activity1

import android.annotation.SuppressLint
import com.bewolf1121.androiddamster.activity1.todoapp.TodoappActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bewolf1121.androiddamster.R
import com.bewolf1121.androiddamster.activity1.settings.SettingsActivity
import com.bewolf1121.androiddamster.activity1.superHeoresApp.SuperHeroListActivity
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroRegisterActivity
import imcCalculator.ImcCalculatorActivity

class MenuApp : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_app)
        //Se crea una int que recorre el Id de btnSaludApp para acceder
        // a la app Salud
        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
        //Al presionar SaludApp nos envia a la funcion navigate que lee
        //SaludApp
        btnSaludApp.setOnClickListener { navigateToSaludApp() }
        //Se crea una int que recorre el Id de ImcCalculator para acceder
        // a la app ImcCalculatorActivity
        val btnImcCalculator = findViewById<Button>(R.id.btnImcCalculator)
        //Al presionar ImcCalculator nos envia a la funcion navigate que lee
        //ImcCalculator
        btnImcCalculator.setOnClickListener { navegateToImcCalculator() }
        //se crea una funcion que recorre el boton TODOAPP
        val btnTodoApp = findViewById<Button>(R.id.btnTodoApp)
        btnTodoApp.setOnClickListener { navigateToTodoApp() }
        //boton de acceso a Activity super Heroes List
        val btnSuperHeroes = findViewById<Button>(R.id.btnSuperHeroes)
        btnSuperHeroes.setOnClickListener { navigateToSuperHeroesList() }
        //boton de acceso para settings
        val btnSettings = findViewById<Button>(R.id.btnSettings)
        btnSettings.setOnClickListener { navigateToSettings() }
        val btnSuperHeroRegister = findViewById<Button>(R.id.btnSuperHeroRegister)
        btnSuperHeroRegister.setOnClickListener { navigateToSuperHeroRegister() }

    }

    private fun navigateToSuperHeroRegister() {
        val intent = Intent(this, SuperHeroRegisterActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    //Crea una funcion que intenta iniciar la siguiente actividad
    private fun navegateToImcCalculator() {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }

    //Crea una funcion que intenta iniciar la siguiente actividad
    private fun navigateToSaludApp() {
        val intent = Intent(this, primeraApp::class.java)
        startActivity(intent)
    }

    private fun navigateToTodoApp() {
        val intent = Intent(this, TodoappActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperHeroesList() {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

}