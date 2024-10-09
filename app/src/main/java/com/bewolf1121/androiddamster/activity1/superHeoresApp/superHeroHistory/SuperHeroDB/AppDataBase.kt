package com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDataConverter.DataConverter
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDao.SearchHistoryDao
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroDataConverter.DateConverter
import com.bewolf1121.androiddamster.activity1.superHeoresApp.superHeroHistory.SuperHeroDB.SuperHeroEntity.SearchHistoryEntity

@Database(entities = [SearchHistoryEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class) // Si tienes conversores, añádelos aquí

abstract class AppDatabase : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "superhero_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}