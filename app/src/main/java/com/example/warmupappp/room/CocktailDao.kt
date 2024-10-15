package com.example.warmupappp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.warmupappp.model.Cocktail

@Dao
interface CocktailDao {
    @Insert
    suspend fun insertCock(cocktail: Cocktail)

    @Query("SELECT * FROM cock_table")
    fun getAllCocktail(): LiveData<List<Cocktail>>

    @Query("DELETE FROM cock_table WHERE name = :cocktailName")
    suspend fun deleteCocktailByName(cocktailName: String)

    @Query("DELETE FROM cock_table")
    suspend fun deleteAll()


    @Query("SELECT EXISTS(SELECT 1 FROM cock_table WHERE name = :cockName LIMIT 1)")
    fun isCockInDatabase(cockName: String): LiveData<Boolean>
}