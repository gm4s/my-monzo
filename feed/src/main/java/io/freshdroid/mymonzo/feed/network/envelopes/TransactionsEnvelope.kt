package io.freshdroid.mymonzo.feed.network.envelopes

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TransactionsEnvelope(
    @Json(name = "transactions") val transactions: List<TransactionEnvelope>
)