package io.freshdroid.mymonzo.feed.network.adapters

import com.squareup.moshi.Moshi
import io.freshdroid.mymonzo.feed.models.Balance
import io.freshdroid.mymonzo.feed.network.envelopes.BalanceEnvelope
import java.util.*

object BalanceAdapter {

    @JvmStatic
    fun fromJson(moshi: Moshi, json: String): Balance {
        val balanceEnvelope = moshi.adapter(BalanceEnvelope::class.java).fromJson(json)
        return Balance(
            balanceEnvelope?.balance ?: 0,
            balanceEnvelope?.spendToday ?: 0,
            balanceEnvelope?.let { Currency.getInstance(it.currency) } ?: Currency.getInstance(Locale.getDefault())
        )
    }

}