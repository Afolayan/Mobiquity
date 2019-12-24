package com.oluwafemi.mobcategories.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: String = "",
    val categoryId: String = "",
    val name: String = "",
    val url: String = "",
    val description: String = "",
    val salePrice: Price,
    val amount: String = "",
    val currency: String = ""
): Parcelable