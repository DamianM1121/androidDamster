package com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.shHistoryViewModel

import androidx.lifecycle.*
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDao.SearchHistoryDao
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// 2. Anotación @HiltViewModel para que Hilt maneje la creación de la clase ViewModel
@HiltViewModel
class SuperHeroViewModel @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao // 3. Inyección del DAO que interactúa con la base de datos
) : ViewModel() {

    // 4. LiveData para observar los cambios en el historial de búsquedas en la UI
    private val _heroSearchHistory = MutableLiveData<List<SearchHistoryEntity>>()
    val heroSearchHistory: LiveData<List<SearchHistoryEntity>> = _heroSearchHistory

    // 5. Función para obtener el historial de búsquedas de la base de datos
    fun getHeroSearchHistory() {
        viewModelScope.launch(Dispatchers.IO) {  // 6. Lanzamos la coroutine en un hilo IO (hilo de entrada/salida) para realizar operaciones de base de datos
            val historyList = searchHistoryDao.getAllSearchHistory() // 7. Llamamos al método del DAO para obtener todos los registros
            _heroSearchHistory.postValue(historyList) // 8. Actualizamos el LiveData para notificar a la UI con los nuevos datos
        }
    }

    // 9. Función para agregar un nuevo héroe al historial de búsqueda
    fun addHeroToHistory(heroName: String, date: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newEntry = SearchHistoryEntity( superheroName = heroName, timestamp = date) // 10. Creamos una nueva entidad con el nombre y la fecha del héroe
            searchHistoryDao.insertSearchHistory(newEntry) // 11. Insertamos el nuevo registro en la base de datos
            getHeroSearchHistory() // 12. Actualizamos el historial después de la inserción
        }
    }
}