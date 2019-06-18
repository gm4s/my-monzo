package io.freshdroid.core.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HttpTransition(
    val verb: HttpVerb,
    val url: String,
    val id: String? = null,
    val accept: String? = null
) : Parcelable