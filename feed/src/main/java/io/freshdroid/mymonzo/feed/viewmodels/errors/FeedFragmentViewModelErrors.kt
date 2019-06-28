package io.freshdroid.mymonzo.feed.viewmodels.errors

import io.freshdroid.mymonzo.core.network.ErrorEnvelope
import io.reactivex.Observable

interface FeedFragmentViewModelErrors {

    fun fetchBalanceApiError(): Observable<ErrorEnvelope>

}