package io.freshdroid.core.network

import com.squareup.moshi.Moshi
import io.freshdroid.core.rx.operators.ApiErrorOperator
import io.freshdroid.core.rx.operators.Operators

open class ApiCore(
    private val moshi: Moshi
) {

    fun apiErrorOperator(): ApiErrorOperator {
        return Operators.apiError(moshi)
    }

}