package io.freshdroid.mymonzo.home.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.autoDisposable
import io.freshdroid.mymonzo.core.rx.Irrelevant
import io.freshdroid.mymonzo.core.ui.IntentExtra
import io.freshdroid.mymonzo.core.viewmodel.ActivityViewModel
import io.freshdroid.mymonzo.home.viewmodels.inputs.HomeViewModelInputs
import io.freshdroid.mymonzo.home.viewmodels.outputs.HomeViewModelOutputs
import io.freshdroid.mymonzo.home.views.FragmentState
import io.freshdroid.mymonzo.navigation.Activities
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class HomeViewModel : ActivityViewModel(), HomeViewModelInputs, HomeViewModelOutputs {

    private val _feedClicked = PublishSubject.create<Irrelevant>()
    private val _summaryClicked = PublishSubject.create<Irrelevant>()
    private val _accountClicked = PublishSubject.create<Irrelevant>()
    private val _helpClicked = PublishSubject.create<Irrelevant>()
    private val _initBottomNavigationBar = PublishSubject.create<FragmentState>()
    private val _displaySelectedFragment = PublishSubject.create<FragmentState>()

    val inputs: HomeViewModelInputs = this
    val outputs: HomeViewModelOutputs = this

    init {
        IntentExtra.getStringFromKey(intent(), Activities.Home.KEY_EXTRA_HOME_ACTIVE_TAB, true)
            .map { FragmentState.stateFromTag(it) }
            .doOnNext(_initBottomNavigationBar::onNext)
            .autoDisposable(this)
            .subscribe(_displaySelectedFragment::onNext)

        _feedClicked
            .autoDisposable(this)
            .subscribe { _displaySelectedFragment.onNext(FragmentState.FEED) }

        _summaryClicked
            .autoDisposable(this)
            .subscribe { _displaySelectedFragment.onNext(FragmentState.SUMMARY) }

        _accountClicked
            .autoDisposable(this)
            .subscribe { _displaySelectedFragment.onNext(FragmentState.ACCOUNT) }

        _helpClicked
            .autoDisposable(this)
            .subscribe { _displaySelectedFragment.onNext(FragmentState.HELP) }
    }

    // INPUTS

    override fun onFeedClick() {
        _feedClicked.onNext(Irrelevant.INSTANCE)
    }

    override fun onSummaryClick() {
        _summaryClicked.onNext(Irrelevant.INSTANCE)
    }

    override fun onAccountClick() {
        _accountClicked.onNext(Irrelevant.INSTANCE)
    }

    override fun onHelpClick() {
        _helpClicked.onNext(Irrelevant.INSTANCE)
    }

    // OUTPUTS

    override fun initBottomNavigationBar(): Observable<FragmentState> = _initBottomNavigationBar

    override fun displaySelectedFragment(): Observable<FragmentState> = _displaySelectedFragment

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel() as T
        }
    }

}