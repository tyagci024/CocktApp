package com.example.warmupappp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cock_table")
data class Cocktail(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // Otomatik artan birincil anahtar
    val name: String,
    val image: String,
    val glass: String?,
    val garnish: String?,
    val howto: String?,
    val flavours: List<Flavour>?, // Null olabilir
    val Time: List<Time>?, // Null olabilir
    val ingredients: List<Ingredient>,
    val nutrition: String?,
    val review: String?,
    val history: String?
) : Parcelable