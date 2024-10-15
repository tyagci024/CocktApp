package com.example.warmupappp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.warmupappp.model.Cocktail
import com.example.warmupappp.room.CocktailDatabase
import com.example.warmupappp.room.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class DetailCocktailViewModel @Inject constructor(
    private val repository: CocktailRepository
) : androidx.lifecycle.ViewModel() {
    var readAllData: LiveData<List<Cocktail>>
    init {
        readAllData = repository.getAllCocktail()
    }

    fun insert(cocktail: Cocktail) {
        viewModelScope.launch {
            repository.insertCocktail(cocktail)
        }
    }

    fun delete(cockName: String) {
            viewModelScope.launch {
                repository.deleteCock(cockName)
            }
        }
    fun isCockDatabase(name: String): LiveData<Boolean> {
        return repository.isCockInDatabase(name)
    }
    }


