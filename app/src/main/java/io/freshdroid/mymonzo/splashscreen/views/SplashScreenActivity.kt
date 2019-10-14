package io.freshdroid.mymonzo.splashscreen.views

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.uber.autodispose.autoDisposable
import io.freshdroid.mymonzo.R
import io.freshdroid.mymonzo.core.extensions.launchIntent
import io.freshdroid.mymonzo.core.rx.transformers.Transformers.observeForUI
import io.freshdroid.mymonzo.core.ui.BaseActivity
import io.freshdroid.mymonzo.core.ui.TransitionUtils.fadeIn
import io.freshdroid.mymonzo.coreComponent
import io.freshdroid.mymonzo.navigation.UriResolver
import io.freshdroid.mymonzo.splashscreen.di.SplashScreenComponentManager
import io.freshdroid.mymonzo.splashscreen.viewmodels.SplashScreenViewModel

class SplashScreenActivity : BaseActivity() {

    private val _component by lazy {
        SplashScreenComponentManager.splashScreenComponent(coreComponent())
    }
    private val _viewModelFactory by lazy {
        SplashScreenViewModel.Factory(_component.environment())
    }
    private val _viewModel by lazy {
        ViewModelProviders.of(this, _viewModelFactory).get(SplashScreenViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        _viewModel.outputs.launchNextActivity()
            .compose(observeForUI())
            .map(UriResolver::resolve)
            .autoDisposable(scopeProvider)
            .subscribe { this.launchIntent(it, fadeIn()) }

        _viewModel.intent(intent)
        _viewModel.inputs.fakeLoading()
    }
}
