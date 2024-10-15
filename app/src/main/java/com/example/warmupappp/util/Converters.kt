package com.example.warmupappp.util

import androidx.room.TypeConverter
import com.example.warmupappp.model.Flavour
import com.example.warmupappp.model.Ingredient
import com.example.warmupappp.model.Time
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    // Flavour listesi için dönüşümler
    @TypeConverter
    fun fromFlavourList(value: List<Flavour>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toFlavourList(value: String?): List<Flavour>? {
        val listType = object : TypeToken<List<Flavour>>() {}.type
        return gson.fromJson(value, listType)
    }

    // Ingredient listesi için dönüşümler
    @TypeConverter
    fun fromIngredientList(value: List<Ingredient>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toIngredientList(value: String?): List<Ingredient>? {
        val listType = object : TypeToken<List<Ingredient>>() {}.type
        return gson.fromJson(value, listType)
    }
    // Ingredient listesi için dönüşümler
    @TypeConverter
    fun fromTimeList(value: List<Time>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toTimeList(value: String?): List<Time>? {
        val listType = object : TypeToken<List<Ingredient>>() {}.type
        return gson.fromJson(value, listType)
    }
}