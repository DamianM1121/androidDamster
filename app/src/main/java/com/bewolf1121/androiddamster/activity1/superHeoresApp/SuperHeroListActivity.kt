package com.bewolf1121.androiddamster.activity1.superHeoresApp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.AppDatabase
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.DetailSuperHeroActivity.Companion.EXTRA_ID
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.DetailSuperHeroActivity
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroRegisterRecyclerView.SuperHeroRegisterAdapter
import com.bewolf1121.androiddamster.databinding.ActivitySuperHeroesListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.util.Date

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroesListBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: SuperHeroAdapter
    private lateinit var SuperHeroRegisterAdapter: SuperHeroRegisterAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ViewBinding es una nueva forma de presentar las vistas
        //binding infla dentro de la Activity las layout
        binding = ActivitySuperHeroesListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
        retrofit = getRetrofit()
        //Carga el historial de busqueda
        loadSearchHistory()

    }

    private fun initUi() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            //se va a llamar cada vez que pulsemos el boton buscar
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            //se llama cada vez que cambiemos el texto
            override fun onQueryTextChange(newText: String?) = false

        })
        adapter = SuperHeroAdapter { navigateToDetail(it) }
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter
    }
    //busca por el nombre los datos del heroe  y los devuelve al historial
    fun searchByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperHeroes(query)
            if (myResponse.isSuccessful) {
                Log.i("AndroidDamster", "si funciona XD")
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("AndroidDamster", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.superheroes)

                        val superheroName = response.superheroes.firstOrNull()?.superheroName ?: "Unknown"
                        insertSearchHistory(superheroName)
                    }
                }
            } else {
                Log.i("AndroidDamster", "no funciona (-_-)")
            }
        }
    }

    private fun loadSearchHistory() {
        CoroutineScope(Dispatchers.IO).launch {
            val searchHistoryList = AppDatabase.getDatabase(applicationContext).searchHistoryDao().getAllSearchHistory()
            runOnUiThread {
                // Actualiza tu RecyclerView con los datos del historial
                val historyAdapter = SuperHeroRegisterAdapter(searchHistoryList.toMutableList())
                binding.rvSuperHero.adapter = historyAdapter
            }
        }
    }

    private fun insertSearchHistory(superheroName: String) {
        if (superheroName.isBlank()) {
            Log.e("AndroidDamster", "Nombre de héroe no válido para insertar en el historial.")
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val searchHistory = SearchHistoryEntity(superheroName = superheroName, timestamp = Date())
                AppDatabase.getDatabase(applicationContext).searchHistoryDao().insertSearchHistory(searchHistory)
                Log.i("AndroidDamster", "Historial de búsqueda insertado correctamente.")
                //Cargar nuevamente el historial despues de la insercion
                loadSearchHistory()

            } catch (e: Exception) {
                Log.e("AndroidDamster", "Error al insertar en la base de datos: ${e.message}")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://superheroapi.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    }

    private fun navigateToDetail(id: String) {
        // Obtener el héroe seleccionado por su id
        val selectedHero = adapter.getHeroById(id)

        // Guardar el nombre y la fecha en la base de datos
        if (selectedHero != null) {
            insertSearchHistory(selectedHero.superheroName)
        }

        // Navegar a la pantalla de detalles
        val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}
