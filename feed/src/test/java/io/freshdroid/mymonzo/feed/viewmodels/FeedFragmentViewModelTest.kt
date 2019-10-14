package io.freshdroid.mymonzo.feed.viewmodels

import io.freshdroid.mymonzo.core.network.ErrorEnvelope
import io.freshdroid.mymonzo.core.user.CurrentUserType
import io.freshdroid.mymonzo.feed.FeedEnvironment
import io.freshdroid.mymonzo.feed.MyMonzoRobolectricTestCase
import io.freshdroid.mymonzo.feed.exceptions.ApiExceptionFactory
import io.freshdroid.mymonzo.feed.factories.BalanceFactory
import io.freshdroid.mymonzo.feed.mocks.MockApiFeed
import io.freshdroid.mymonzo.feed.models.Balance
import io.freshdroid.mymonzo.feed.network.ApiFeedType
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

internal class FeedFragmentViewModelTest : MyMonzoRobolectricTestCase() {

    private lateinit var environment: FeedEnvironment

    @Before
    fun initComponent() {
        environment = FeedEnvironment(
            mock(CurrentUserType::class.java),
            mock(ApiFeedType::class.java),
            mock(Scheduler::class.java)
        )
    }

    @Test
    fun testCurrentBalance() {
        val environment = environment.copy(
            apiFeed = object : MockApiFeed() {
                override fun fetchBalance(): Observable<Balance> {
                    return Observable.just(BalanceFactory.creator())
                }
            }
        )
        val vm = FeedFragmentViewModel(environment)
        val currentBalance = TestSubscriber<Balance>()
        vm.outputs.currentBalance().subscribe(currentBalance::onNext)

        vm.inputs.fetchCurrentBalance()
        currentBalance.assertValue { it.now == 1000 && it.spendToday == 10 }
    }

    @Test
    fun testFetchBalanceApiError() {
        val environment = environment.copy(
            apiFeed = object : MockApiFeed() {
                override fun fetchBalance(): Observable<Balance> {
                    return Observable.error(ApiExceptionFactory.badRequestException())
                }
            }
        )
        val vm = FeedFragmentViewModel(environment)
        val fetchBalanceApiError = TestSubscriber<ErrorEnvelope>()
        vm.errors.fetchBalanceApiError().subscribe(fetchBalanceApiError::onNext)

        vm.inputs.fetchCurrentBalance()
        fetchBalanceApiError.assertValue { it.responseCode == 400 && it.message == "bad request" }
    }

}