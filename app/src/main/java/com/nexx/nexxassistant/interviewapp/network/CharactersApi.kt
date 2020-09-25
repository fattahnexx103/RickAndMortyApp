package com.nexx.nexxassistant.interviewapp.network

import com.nexx.nexxassistant.interviewapp.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    //We pass the page number as query param
    //the call retrieves an object of Character which contains info object and results object array
    @GET("/api/character/")
    suspend fun getCharacters(@Query("page") page: Int): Response<Character>

}