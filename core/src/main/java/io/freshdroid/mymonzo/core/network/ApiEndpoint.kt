package io.freshdroid.mymonzo.core.network

import io.freshdroid.mymonzo.core.BuildConfig

internal enum class ApiEndpoint(
    val url: String
) {
    MONZO(BuildConfig.BASE_URL)
}