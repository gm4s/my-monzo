package io.freshdroid.mymonzo.feed.viewmodels.outputs

import io.freshdroid.mymonzo.feed.models.Balance
import io.reactivex.Observable

interface FeedFragmentViewModelOutputs {

    fun currentBalance(): Observable<Balance>

}