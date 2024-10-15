package com.example.warmupappp.service

import android.util.Log
import com.example.warmupappp.model.Cocktail
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CocktailService {
    companion object {
        const val URL = "https://raw.githubusercontent.com/"
    }

    private val BASE_URL = URL
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CocktailApi::class.java)

    suspend fun getCoinList(): List<Cocktail> {
        val response = api.getList()
        Log.d("CocktailService", "Fetched ${response.size} cocktails")
        return response
    }
}
