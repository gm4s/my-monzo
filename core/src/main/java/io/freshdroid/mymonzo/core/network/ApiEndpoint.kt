package io.freshdroid.mymonzo.core.network

import io.freshdroid.mymonzo.core.BuildConfig


enum class ApiEndpoint(
    val url: String
) {
    MONZO(BuildConfig.BASE_URL)
}