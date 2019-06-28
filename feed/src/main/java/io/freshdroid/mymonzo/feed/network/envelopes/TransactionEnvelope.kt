package io.freshdroid.mymonzo.feed.network.envelopes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionEnvelope(
    @Json(name = "id") val id: String,
    @Json(name = "created") val created: String,
    @Json(name = "description") val description: String?,
    @Json(name = "amount") val amount: Int,
    @Json(name = "currency") val currency: String,
    @Json(name = "merchant") val merchant: MerchantEnvelope?
)