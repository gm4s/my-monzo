package io.freshdroid.mymonzo.core.rx.operators

import com.squareup.moshi.Moshi

object Operators {

    @JvmStatic
    fun apiError(moshi: Moshi): ApiErrorOperator = ApiErrorOperator(moshi)

}