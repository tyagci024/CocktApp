package com.example.warmupappp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warmupappp.model.Cocktail
import com.example.warmupappp.room.CocktailRepository
import com.example.warmupappp.service.CocktailService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(
    private val repository: CocktailRepository
) : ViewModel() {
    private var _cocktails = MutableLiveData<List<Cocktail>>()
    val cocktails: LiveData<List<Cocktail>> get() = _cocktails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchCocktails()
    }

    fun fetchCocktails() {
        viewModelScope.launch {
            try {
                Log.d("CocktailViewModel", "Veri çekme işlemi başlatıldı.")
                val result = repository.fetchAllData() // Burayı LiveData döndürüyorsa göz önünde bulundurun
                _cocktails.value = result // Burada result doğrudan List<Cocktail> ise, LiveData'ya uygun bir dönüşüm yapmalısınız
                Log.d("CocktailViewModel", "Veri başarıyla çekildi: ${result.size} adet kokteyl bulundu.")
                _error.value = "false"
            } catch (e: Exception) {
                Log.e("CocktailViewModel", "Veri çekme hatası: ${e.message}", e)
                _error.value = "true"
            }
        }
    }

}