package com.example.warmupappp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flavour(
    val flavour: String // Bu alan zorunlu
) : Parcelable
