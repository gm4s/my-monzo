package io.freshdroid.mymonzo.core.network.exceptions

import io.freshdroid.mymonzo.core.network.ErrorEnvelope
import retrofit2.Response

open class ApiException(
    private val errorEnvelope: ErrorEnvelope,
    response: Response<*>
) : ResponseException(response) {

    fun errorEnvelope(): ErrorEnvelope = errorEnvelope

}