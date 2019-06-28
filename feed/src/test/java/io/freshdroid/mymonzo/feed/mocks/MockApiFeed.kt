package io.freshdroid.mymonzo.feed.mocks

import io.freshdroid.mymonzo.feed.models.Balance
import io.freshdroid.mymonzo.feed.network.ApiFeedType
import io.freshdroid.mymonzo.models.Transaction
import io.reactivex.Observable

open class MockApiFeed: ApiFeedType {

    override fun fetchBalance(): Observable<Balance> {
        return Observable.empty()
    }

    override fun fetchFeed(): Observable<ArrayList<Transaction>> {
        return Observable.empty()
    }

}