package com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // ID autogenerado
    //val superheroId: String, // ID del superhéroe
    val superheroName: String, // Nombre del superhéroe
    val timestamp: Date // Marca de tiempo para cuando se realizó la búsqueda
)
