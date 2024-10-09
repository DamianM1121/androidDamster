package com.bewolf1121.androiddamster.activity1.settings

import android.os.Vibrator

data class SettingsModel(
    var volume: Int,
    var darkmode: Boolean,
    var bluetooth: Boolean,
    var vibrator: Boolean
)