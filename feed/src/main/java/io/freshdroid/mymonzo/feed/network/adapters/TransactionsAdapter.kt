package io.freshdroid.mymonzo.feed.network.adapters

import com.squareup.moshi.Moshi
import io.freshdroid.mymonzo.feed.network.envelopes.TransactionsEnvelope
import io.freshdroid.mymonzo.models.Transaction

object TransactionsAdapter {

    @JvmStatic
    fun fromJson(moshi: Moshi, json: String): ArrayList<Transaction> {
        val transactionsEnvelope = moshi.adapter(TransactionsEnvelope::class.java).fromJson(json)
        val transactions = ArrayList<Transaction>()

        transactionsEnvelope?.transactions?.forEach { transactionEnvelope ->
            transactions.add(
                Transaction(
                    transactionEnvelope.id,
                    transactionEnvelope.created,
                    transactionEnvelope.description,
                    transactionEnvelope.amount,
                    transactionEnvelope.currency,
                    MerchantAdapter.fromEnvelope(transactionEnvelope.merchant)
                )
            )
        }

        return transactions
    }

}