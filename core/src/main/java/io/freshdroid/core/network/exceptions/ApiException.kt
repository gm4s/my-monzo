package io.freshdroid.core.network.exceptions

import io.freshdroid.core.network.ErrorEnvelope
import retrofit2.Response

open class ApiException(
    private val errorEnvelope: ErrorEnvelope,
    response: Response<*>
) : ResponseException(response) {

    fun errorEnvelope(): ErrorEnvelope = errorEnvelope

}