package io.freshdroid.mymonzo.feed.network.envelopes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MerchantEnvelope(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "logo") val logo: String?,
    @Json(name = "category") val category: String
)