package io.freshdroid.mymonzo.feed.models

import java.util.*

data class Balance(
    val now: Int,
    val spendToday: Int,
    val currency: Currency
)