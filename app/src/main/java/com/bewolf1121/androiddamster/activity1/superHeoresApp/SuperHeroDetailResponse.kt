package com.bewolf1121.androiddamster.activity1.superHeoresApp

import com.google.gson.annotations.SerializedName

//identifica mediante el nombre del JSON el valor de acceso y el tipo : en este caso String
data class SuperHeroDetailResponse(
    @SerializedName("name") val name: String,
    @SerializedName("powerstats") val powerstats: PowerStatsResponse,
    @SerializedName("image") val image: SuperHeroImageDetailResponse,
    @SerializedName("biography") val biography: SuperHeroBiography
)

//devuelve cada uno de los valores alojados dentro del objeto powerstats siendo estos de tipo String
data class PowerStatsResponse(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class SuperHeroImageDetailResponse(@SerializedName("url") val url: String)

data class SuperHeroBiography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String
)
