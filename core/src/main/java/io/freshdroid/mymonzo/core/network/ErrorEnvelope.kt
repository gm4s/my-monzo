package io.freshdroid.mymonzo.core.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.freshdroid.mymonzo.core.network.exceptions.ApiException

@JsonClass(generateAdapter = true)
data class ErrorEnvelope(
    @Json(name = "error") val error: String? = null,
    @Json(name = "code") val code: Int = -1
) {
    companion object {
        fun fromThrowable(t: Throwable): ErrorEnvelope? {
            if (t is ApiException) {
                return t.errorEnvelope()
            }
            return null
        }
    }
}