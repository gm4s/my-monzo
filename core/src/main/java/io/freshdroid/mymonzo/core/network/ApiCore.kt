package io.freshdroid.mymonzo.core.network

import com.squareup.moshi.Moshi
import io.freshdroid.mymonzo.core.rx.operators.ApiErrorOperator
import io.freshdroid.mymonzo.core.rx.operators.Operators

open class ApiCore(
    private val moshi: Moshi
) {

    fun apiErrorOperator(): ApiErrorOperator {
        return Operators.apiError(moshi)
    }

}