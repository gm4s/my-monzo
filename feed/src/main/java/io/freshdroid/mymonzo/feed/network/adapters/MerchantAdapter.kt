package io.freshdroid.mymonzo.feed.network.adapters

import io.freshdroid.mymonzo.feed.network.envelopes.MerchantEnvelope
import io.freshdroid.mymonzo.models.Merchant

object MerchantAdapter {

    @JvmStatic
    fun fromEnvelope(merchantEnvelope: MerchantEnvelope?): Merchant? {
        return merchantEnvelope?.let {
            Merchant(
                merchantEnvelope.id,
                merchantEnvelope.name,
                merchantEnvelope.logo,
                merchantEnvelope.category
            )
        }
    }

}