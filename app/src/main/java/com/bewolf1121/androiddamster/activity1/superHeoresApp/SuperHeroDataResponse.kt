package com.bewolf1121.androiddamster.activity1.superHeoresApp

import android.media.Image
import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superheroes: List<SuperHeroItemResponse>
)

data class SuperHeroItemResponse(
    @SerializedName("id") val superheroId: String,
    @SerializedName("name") val superheroName: String,
    @SerializedName("image") val superheroImage: SuperHeroImageResponse

)

data class SuperHeroImageResponse(@SerializedName("url") val url: String)