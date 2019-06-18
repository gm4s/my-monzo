package io.freshdroid.mymonzo.splashscreen.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.freshdroid.core.rx.Irrelevant
import io.freshdroid.core.viewmodel.ActivityViewModel
import io.freshdroid.mymonzo.navigation.ApplicationMap
import io.freshdroid.mymonzo.splashscreen.SplashScreenEnvironment
import io.freshdroid.mymonzo.splashscreen.di.SplashScreenComponentManager
import io.freshdroid.mymonzo.splashscreen.viewmodels.errors.SplashScreenViewModelErrors
import io.freshdroid.mymonzo.splashscreen.viewmodels.inputs.SplashScreenViewModelInputs
import io.freshdroid.mymonzo.splashscreen.viewmodels.outputs.SplashScreenViewModelOutputs
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SplashScreenViewModel(
    environment: SplashScreenEnvironment,
    scopeProvider: AndroidLifecycleScopeProvider
) : ActivityViewModel(), SplashScreenViewModelInputs, SplashScreenViewModelOutputs, SplashScreenViewModelErrors {

    private val mFakeLoading = PublishSubject.create<Irrelevant>()
    private val mLaunchNextActivity = PublishSubject.create<Uri>()

    private val mScheduler = environment.scheduler

    val inputs: SplashScreenViewModelInputs = this
    val outputs: SplashScreenViewModelOutputs = this
    val errors: SplashScreenViewModelErrors = this

    init {
        mFakeLoading
            .delay(1000, TimeUnit.MILLISECONDS, mScheduler)
            .autoDisposable(scopeProvider)
            .subscribe { mLaunchNextActivity.onNext(Uri.parse(ApplicationMap.Home.FEED)) }
    }

    override fun onCleared() {
        super.onCleared()
        SplashScreenComponentManager.destroySplashScreenComponent()
    }

    // INPUTS

    override fun fakeLoading() {
        mFakeLoading.onNext(Irrelevant.INSTANCE)
    }

    // OUTPUTS

    override fun launchNextActivity(): Observable<Uri> = mLaunchNextActivity

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val environment: SplashScreenEnvironment,
        private val scopeProvider: AndroidLifecycleScopeProvider
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SplashScreenViewModel(environment, scopeProvider) as T
        }
    }

}