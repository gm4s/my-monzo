package io.freshdroid.mymonzo.splashscreen.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.autoDisposable
import io.freshdroid.mymonzo.core.BuildConfig
import io.freshdroid.mymonzo.core.rx.Irrelevant
import io.freshdroid.mymonzo.core.viewmodel.ActivityViewModel
import io.freshdroid.mymonzo.navigation.ApplicationMap
import io.freshdroid.mymonzo.splashscreen.SplashScreenEnvironment
import io.freshdroid.mymonzo.splashscreen.di.SplashScreenComponentManager
import io.freshdroid.mymonzo.splashscreen.viewmodels.inputs.SplashScreenViewModelInputs
import io.freshdroid.mymonzo.splashscreen.viewmodels.outputs.SplashScreenViewModelOutputs
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SplashScreenViewModel(
    environment: SplashScreenEnvironment
) : ActivityViewModel(), SplashScreenViewModelInputs, SplashScreenViewModelOutputs {

    private val _fakeLoading = PublishSubject.create<Irrelevant>()
    private val _launchNextActivity = PublishSubject.create<Uri>()

    private val _currentUser = environment.currentUser
    private val _scheduler = environment.scheduler

    val inputs: SplashScreenViewModelInputs = this
    val outputs: SplashScreenViewModelOutputs = this

    init {
        _fakeLoading
            .doOnNext { _currentUser.setAccessToken(BuildConfig.USER_TOKEN) }
            .delay(1000, TimeUnit.MILLISECONDS, _scheduler)
            .autoDisposable(this)
            .subscribe { _launchNextActivity.onNext(Uri.parse(ApplicationMap.Home.FEED)) }
    }

    override fun onCleared() {
        super.onCleared()
        SplashScreenComponentManager.destroySplashScreenComponent()
    }

    // INPUTS

    override fun fakeLoading() {
        _fakeLoading.onNext(Irrelevant.INSTANCE)
    }

    // OUTPUTS

    override fun launchNextActivity(): Observable<Uri> = _launchNextActivity

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val _environment: SplashScreenEnvironment
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SplashScreenViewModel(_environment) as T
        }
    }
}
