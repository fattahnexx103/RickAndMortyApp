package com.nexx.nexxassistant.interviewapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Singleton Object
object CharacterService{

    //Base URL for retrofit
    private val BASE_URL = "https://rickandmortyapi.com"

     //returns a retrofit instance with the api specified
     fun getApiService(): CharactersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersApi::class.java)
    }
}
