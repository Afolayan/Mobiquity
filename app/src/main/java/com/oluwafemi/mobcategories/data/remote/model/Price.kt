package com.oluwafemi.mobcategories.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    val amount: String = "",
    val currency: String = ""
): Parcelable