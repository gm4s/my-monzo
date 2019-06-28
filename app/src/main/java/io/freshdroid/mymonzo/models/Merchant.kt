package io.freshdroid.mymonzo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Merchant(
    val id: String,
    val name: String,
    val logo: String?,
    val category: String
) : Parcelable