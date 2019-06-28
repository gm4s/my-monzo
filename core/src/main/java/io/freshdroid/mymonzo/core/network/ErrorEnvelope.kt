package io.freshdroid.mymonzo.core.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.freshdroid.mymonzo.core.network.exceptions.ApiException

@JsonClass(generateAdapter = true)
data class ErrorEnvelope(
    @Json(name = "message") val message: String? = null,
    val responseCode: Int = -1
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