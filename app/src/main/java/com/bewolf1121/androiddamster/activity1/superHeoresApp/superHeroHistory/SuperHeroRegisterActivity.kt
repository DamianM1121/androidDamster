package com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.AppDatabase
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroRegisterRecyclerView.SuperHeroRegisterAdapter
import com.bewolf1121.androiddamster.databinding.ActivitySuperHeroRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SuperHeroRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroRegisterBinding
    private lateinit var adapter: SuperHeroRegisterAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Implementación del binding dentro del activity
        binding = ActivitySuperHeroRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Iniciamos la base de datos y el adapter
        database = AppDatabase.getDatabase(this)
        adapter = SuperHeroRegisterAdapter(mutableListOf())

        //Configura el RecyclerView
        binding.rvSuperHeroRegister.adapter = adapter
        binding.rvSuperHeroRegister.layoutManager = LinearLayoutManager(this)
    }


}

