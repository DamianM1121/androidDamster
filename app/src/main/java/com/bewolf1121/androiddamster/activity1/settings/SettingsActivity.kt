package com.bewolf1121.androiddamster.activity1.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bewolf1121.androiddamster.R
import com.bewolf1121.androiddamster.databinding.ActivitySettingsBinding
import com.bewolf1121.androiddamster.databinding.ActivitySuperHeroesListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

//el by delega una unica instancia de la base de datos
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    //llamo al binding para acceder a las vistas
    private lateinit var binding: ActivitySettingsBinding

    private var firstTime: Boolean = true

    //creo una constante para trabajar en el proyecto, el valor en mayuscula y el nombre en minuscula
    companion object {
        const val VOLUME_LVL = "volume_lvl"
        const val KEY_DARK_MODE = "key_dark_mode"
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_VIBRATOR = "key_vibrator"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        CoroutineScope(Dispatchers.IO).launch {
            getSettings().filter { firstTime }.collect { settingsModel ->
                //si es disitinto de null se ejecuta todo en el hilo principal
                // una coroutine no puede ejecutar cambios visuales por eso runOnUiThreads
                if (settingsModel != null) {
                    runOnUiThread {
                        binding.switchDarkMode.isChecked = settingsModel.darkmode
                        binding.switchBluetooth.isChecked = settingsModel.bluetooth
                        binding.switchVibrator.isChecked = settingsModel.vibrator
                        binding.rsVolume.setValues(settingsModel.volume.toFloat())
                        firstTime = !firstTime
                    }
                }
            }
        }

    }

    private fun initUI() {
        //El binding llama al rsVolume y cuando cambie recorre el valor y lo muestra en el log
        //la coroutine se usa para lanzar en segundo plano el save volume y lo devuelve de float a int
        binding.rsVolume.addOnChangeListener { _, value, _ ->
            Log.i("AndroidDamsterSettings", "El valor es $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
        }
        //realiza el mismo cambio pero en boolean para el dark mode y su switch
        binding.switchDarkMode.setOnCheckedChangeListener { _, value ->
            //activa el modo oscuro si esta en true
            if (value) {
                enableDarkMode()
            } else {//Desactiva el modo oscuro si esta en false
                disableDarkMode()
            }
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARK_MODE, value)
            }
        }

        binding.switchBluetooth.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH, value)
            }
        }

        binding.switchVibrator.setOnCheckedChangeListener { _, value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATOR, value)
            }
        }
    }

    private suspend fun saveVolume(value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LVL)] = value
        }
    }

    private suspend fun saveOptions(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    //Llamamos al objeto de data class settings model y retornamos sus valores y si son nulos devolvemos
    // la excepcion con ?:
    private fun getSettings(): Flow<SettingsModel?> {
        return dataStore.data.map { preferences ->
            SettingsModel(
                volume = preferences[intPreferencesKey(VOLUME_LVL)] ?: 50,
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: true,
                darkmode = preferences[booleanPreferencesKey(KEY_DARK_MODE)] ?: false,
                vibrator = preferences[booleanPreferencesKey(KEY_VIBRATOR)] ?: true

            )
        }
    }

    private fun enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}
