package com.example.warmupappp.room

import androidx.lifecycle.LiveData
import com.example.warmupappp.model.Cocktail
import com.example.warmupappp.service.CocktailApi
import javax.inject.Inject

class CocktailRepository@Inject constructor(
    private val api: CocktailApi,
    private val cocktailDao: CocktailDao
) {
    fun getAllCocktail(): LiveData<List<Cocktail>> {
        return cocktailDao.getAllCocktail()
    }
    suspend fun insertCocktail(cocktail: Cocktail) {
        cocktailDao.insertCock(cocktail)
    }
    suspend fun deleteCock(cocktailName: String) {
        cocktailDao.deleteCocktailByName(cocktailName)
    }
    fun isCockInDatabase(name: String): LiveData<Boolean> {
        return cocktailDao.isCockInDatabase(name)
    }
    suspend fun fetchAllData() = api.getList()

}