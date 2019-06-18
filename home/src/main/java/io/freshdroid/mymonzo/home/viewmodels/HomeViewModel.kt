package io.freshdroid.mymonzo.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.freshdroid.core.rx.Irrelevant
import io.freshdroid.core.ui.IntentExtra
import io.freshdroid.core.viewmodel.ActivityViewModel
import io.freshdroid.mymonzo.home.viewmodels.inputs.HomeViewModelInputs
import io.freshdroid.mymonzo.home.viewmodels.outputs.HomeViewModelOutputs
import io.freshdroid.mymonzo.home.views.FragmentState
import io.freshdroid.mymonzo.navigation.Activities
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class HomeViewModel(
    scopeProvider: AndroidLifecycleScopeProvider
) : ActivityViewModel(), HomeViewModelInputs, HomeViewModelOutputs {

    private val mFeedClicked = PublishSubject.create<Irrelevant>()
    private val mSummaryClicked = PublishSubject.create<Irrelevant>()
    private val mAccountClicked = PublishSubject.create<Irrelevant>()
    private val mHelpClicked = PublishSubject.create<Irrelevant>()
    private val mInitBottomNavigationBar = PublishSubject.create<FragmentState>()
    private val mDisplaySelectedFragment = PublishSubject.create<FragmentState>()

    val inputs: HomeViewModelInputs = this
    val outputs: HomeViewModelOutputs = this

    init {
        IntentExtra.getStringFromKey(intent(), Activities.Home.KEY_EXTRA_HOME_ACTIVE_TAB, true)
            .map { FragmentState.stateFromTag(it) }
            .doOnNext(mInitBottomNavigationBar::onNext)
            .autoDisposable(scopeProvider)
            .subscribe(mDisplaySelectedFragment::onNext)

        mFeedClicked
            .autoDisposable(scopeProvider)
            .subscribe { mDisplaySelectedFragment.onNext(FragmentState.FEED) }

        mSummaryClicked
            .autoDisposable(scopeProvider)
            .subscribe { mDisplaySelectedFragment.onNext(FragmentState.SUMMARY) }

        mAccountClicked
            .autoDisposable(scopeProvider)
            .subscribe { mDisplaySelectedFragment.onNext(FragmentState.ACCOUNT) }

        mHelpClicked
            .autoDisposable(scopeProvider)
            .subscribe { mDisplaySelectedFragment.onNext(FragmentState.HELP) }
    }

    // INPUTS

    override fun onFeedClick() {
        mFeedClicked.onNext(Irrelevant.INSTANCE)
    }

    override fun onSummaryClick() {
        mSummaryClicked.onNext(Irrelevant.INSTANCE)
    }

    override fun onAccountClick() {
        mAccountClicked.onNext(Irrelevant.INSTANCE)
    }

    override fun onHelpClick() {
        mHelpClicked.onNext(Irrelevant.INSTANCE)
    }

    // OUTPUTS

    override fun initBottomNavigationBar(): Observable<FragmentState> = mInitBottomNavigationBar

    override fun displaySelectedFragment(): Observable<FragmentState> = mDisplaySelectedFragment

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val scopeProvider: AndroidLifecycleScopeProvider
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(scopeProvider) as T
        }
    }

}