package io.freshdroid.mymonzo.feed.network.envelopes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BalanceEnvelope(
    @Json(name = "balance") val balance: Int,
    @Json(name = "currency") val currency: String,
    @Json(name = "spend_today") val spendToday: Int
)