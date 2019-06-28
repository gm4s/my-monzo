package io.freshdroid.mymonzo.feed.network

import io.freshdroid.mymonzo.feed.models.Balance
import io.freshdroid.mymonzo.models.Transaction
import io.reactivex.Observable

interface ApiFeedType {

    fun fetchBalance(): Observable<Balance>

    fun fetchFeed(): Observable<ArrayList<Transaction>>

}