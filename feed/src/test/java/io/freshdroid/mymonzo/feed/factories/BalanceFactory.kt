package io.freshdroid.mymonzo.feed.factories

import io.freshdroid.mymonzo.feed.models.Balance
import java.util.*

object BalanceFactory {

    @JvmStatic
    fun creator(): Balance {
        return Balance(
            1000,
            10,
            Currency.getInstance("GBP")
        )
    }

}