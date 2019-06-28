package io.freshdroid.mymonzo.feed.exceptions

import io.freshdroid.mymonzo.core.network.ErrorEnvelope
import io.freshdroid.mymonzo.core.network.exceptions.ApiException
import okhttp3.ResponseBody
import retrofit2.Response

internal object ApiExceptionFactory {

    fun badRequestException(): ApiException {
        val envelope = ErrorEnvelope(
            "bad request",
            400
        )
        val body = ResponseBody.create(null, "")
        val response = Response.error<ResponseBody>(400, body)

        return ApiException(envelope, response)
    }

    fun resourceNotFoundException(): ApiException {
        val envelope = ErrorEnvelope(
            "resource not found",
            404
        )
        val body = ResponseBody.create(null, "")
        val response = Response.error<ResponseBody>(404, body)

        return ApiException(envelope, response)
    }

}