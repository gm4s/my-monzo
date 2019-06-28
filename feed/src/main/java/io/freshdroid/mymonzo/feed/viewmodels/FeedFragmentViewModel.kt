package io.freshdroid.mymonzo.feed.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.freshdroid.mymonzo.core.network.ErrorEnvelope
import io.freshdroid.mymonzo.core.rx.Irrelevant
import io.freshdroid.mymonzo.core.rx.transformers.Transformers.pipeApiErrorsTo
import io.freshdroid.mymonzo.core.rx.transformers.Transformers.pipeErrorsTo
import io.freshdroid.mymonzo.core.viewmodel.FragmentViewModel
import io.freshdroid.mymonzo.feed.FeedEnvironment
import io.freshdroid.mymonzo.feed.di.FeedComponentManager
import io.freshdroid.mymonzo.feed.models.Balance
import io.freshdroid.mymonzo.feed.viewmodels.errors.FeedFragmentViewModelErrors
import io.freshdroid.mymonzo.feed.viewmodels.inputs.FeedFragmentViewModelInputs
import io.freshdroid.mymonzo.feed.viewmodels.outputs.FeedFragmentViewModelOutputs
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class FeedFragmentViewModel(
    environment: FeedEnvironment,
    scopeProvider: AndroidLifecycleScopeProvider
) : FragmentViewModel(), FeedFragmentViewModelInputs, FeedFragmentViewModelOutputs, FeedFragmentViewModelErrors {

    private val _fetchCurrentBalance = PublishSubject.create<Irrelevant>()
    private val _currentBalance = PublishSubject.create<Balance>()
    private val _fetchBalanceApiError = PublishSubject.create<ErrorEnvelope>()
    private val _fetchBalanceError = PublishSubject.create<Throwable>()

    private val _apiFeed = environment.apiFeed

    val inputs: FeedFragmentViewModelInputs = this
    val outputs: FeedFragmentViewModelOutputs = this
    val errors: FeedFragmentViewModelErrors = this

    init {
        _fetchCurrentBalance
            .switchMap { this.fetchBalance() }
            .autoDisposable(scopeProvider)
            .subscribe(_currentBalance::onNext)
    }

    override fun onCleared() {
        super.onCleared()
        FeedComponentManager.destroyFeedComponent()
    }

    // INPUTS

    override fun fetchCurrentBalance() {
        _fetchCurrentBalance.onNext(Irrelevant.INSTANCE)
    }

    // OUTPUTS

    override fun currentBalance(): Observable<Balance> = _currentBalance

    // ERRORS

    override fun fetchBalanceApiError(): Observable<ErrorEnvelope> = _fetchBalanceApiError

    private fun fetchBalance(): Observable<Balance> {
        return _apiFeed.fetchBalance()
            .compose(pipeApiErrorsTo(_fetchBalanceApiError))
            .compose(pipeErrorsTo(_fetchBalanceError))
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val _environment: FeedEnvironment,
        private val _scopeProvider: AndroidLifecycleScopeProvider
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FeedFragmentViewModel(_environment, _scopeProvider) as T
        }
    }

}