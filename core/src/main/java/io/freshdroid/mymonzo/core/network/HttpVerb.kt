package io.freshdroid.mymonzo.core.network

enum class HttpVerb(
    private val _tag: String
) {

    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    companion object {
        @JvmStatic
        fun verbFromTag(tag: String): HttpVerb {
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