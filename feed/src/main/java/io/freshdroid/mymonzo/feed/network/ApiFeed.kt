package io.freshdroid.mymonzo.feed.network

import com.squareup.moshi.Moshi
import io.freshdroid.mymonzo.core.BuildConfig
import io.freshdroid.mymonzo.core.network.*
import io.freshdroid.mymonzo.feed.models.Balance
import io.freshdroid.mymonzo.feed.network.adapters.BalanceAdapter
import io.freshdroid.mymonzo.feed.network.adapters.TransactionsAdapter
import io.freshdroid.mymonzo.models.Transaction
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class ApiFeed(
    private val _httpTransitionFactory: HttpTransitionFactoryType,
    private val _moshi: Moshi
) : ApiCore(_moshi), ApiFeedType {

    override fun fetchBalance(): Observable<Balance> {
        val httpTransition = HttpTransition(
            verb = HttpVerb.GET,
            url = "${ApiEndpoint.MONZO.url}/balance?account_id=${BuildConfig.ACCOUNT_ID}"
        )

        return _httpTransitionFactory.create(transition = httpTransition)
            .lift(apiErrorOperator())
            .map { BalanceAdapter.fromJson(_moshi, it) }
            .subscribeOn(Schedulers.io())
    }

    override fun fetchFeed(): Observable<ArrayList<Transaction>> {
        val httpTransition = HttpTransition(
            verb = HttpVerb.GET,
            url = "${ApiEndpoint.MONZO.url}/transactions?expand[]=merchant&account_id=${BuildConfig.ACCOUNT_ID}&limit=20"
        )

        return _httpTransitionFactory.create(transition = httpTransition)
            .lift(apiErrorOperator())
            .map { TransactionsAdapter.fromJson(_moshi, it) }
            .subscribeOn(Schedulers.io())
    }

}