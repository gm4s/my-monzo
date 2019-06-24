package io.freshdroid.mymonzo.viewmodels

import android.net.Uri
import io.freshdroid.mymonzo.MyMonzoRobolectricTestCase
import io.freshdroid.mymonzo.navigation.ApplicationMap
import io.freshdroid.mymonzo.splashscreen.SplashScreenEnvironment
import io.freshdroid.mymonzo.splashscreen.di.DaggerSplashScreenComponent
import io.freshdroid.mymonzo.splashscreen.di.SplashScreenComponent
import io.freshdroid.mymonzo.splashscreen.viewmodels.SplashScreenViewModel
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

internal class SplashScreenViewModelTest : MyMonzoRobolectricTestCase() {

    private lateinit var component: SplashScreenComponent

    @Before
    fun initComponent() {
        component = DaggerSplashScreenComponent.builder().coreComponent(coreComponent()).build()
    }

    private fun environment(): SplashScreenEnvironment = component.environment()

    @Test
    fun testLaunchNextActivity() {
        val testScheduler = TestScheduler()
        val environment = environment().copy(
            scheduler = testScheduler
        )
        val vm = SplashScreenViewModel(environment, scopeProvider())
        val launchNextActivity = TestSubscriber<Uri>()
        vm.outputs.launchNextActivity().subscribe(launchNextActivity::onNext)

        vm.inputs.fakeLoading()
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        launchNextActivity.assertValue { it == Uri.parse(ApplicationMap.Home.FEED) }
    }

}