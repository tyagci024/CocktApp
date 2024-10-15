package com.example.warmupappp.service

import com.example.warmupappp.model.Cocktail
import com.example.warmupappp.util.Constants
import retrofit2.http.GET

interface CocktailApi {

    @GET(Constants.END_POINT_COCK)
    suspend fun getList(): List<Cocktail>
}