package io.freshdroid.mymonzo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transaction(
    val id: String,
    val created: String,
    val description: String?,
    val amount: Int,
    val currency: String,
    val merchant: Merchant?
) : Parcelable