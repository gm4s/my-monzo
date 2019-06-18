package io.freshdroid.core.network

enum class HttpVerb(
        private val tag: String
) {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    companion object {
        @JvmStatic
        fun protocolFromTag(tag: String): HttpVerb {
            return when (tag.toUpperCase()) {
                "GET" -> GET
                "POST" -> POST
                "PUT" -> PUT
                "PATCH" -> PATCH
                else -> DELETE
            }
        }
    }

}