package io.freshdroid.core.network

import io.freshdroid.core.BuildConfig

internal enum class ApiEndpoint(
    val url: String
) {
    MONZO(BuildConfig.BASE_URL)
}