package com.example.warmupappp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.warmupappp.model.Cocktail
import com.example.warmupappp.util.Converters

@Database(entities = [Cocktail::class], version = 13, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao

}