package com.bewolf1121.androiddamster.activity1.superHeoresApp

import android.media.Image
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //en este caso responde a traves del metodo obtener super heroes recorriendo la lista del
    //SuperHeroDataResponse
    @GET("api.php/e0db9d2cb719ea8300017750926d1a48/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superheroName: String): Response<SuperHeroDataResponse>

    //en este caso responde a traves del metodo obtener detalles de super heroes recorriendo la
    //lista del SuperHeroDetailResponse
    @GET("/api.php/e0db9d2cb719ea8300017750926d1a48/{id}")
    suspend fun getSuperHeroDetail(@Path("id") superHeroId: String): Response<SuperHeroDetailResponse>

}