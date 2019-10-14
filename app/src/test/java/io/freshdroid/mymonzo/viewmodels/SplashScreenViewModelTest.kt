package io.freshdroid.mymonzo.viewmodels

import android.net.Uri
import io.freshdroid.mymonzo.MyMonzoRobolectricTestCase
import io.freshdroid.mymonzo.core.user.CurrentUserType
import io.freshdroid.mymonzo.navigation.ApplicationMap
import io.freshdroid.mymonzo.splashscreen.SplashScreenEnvironment
import io.freshdroid.mymonzo.splashscreen.viewmodels.SplashScreenViewModel
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.concurrent.TimeUnit

internal class SplashScreenViewModelTest : MyMonzoRobolectricTestCase() {

    private lateinit var environment: SplashScreenEnvironment

    @Before
    fun initComponent() {
        environment = SplashScreenEnvironment(
            mock(CurrentUserType::class.java),
            mock(Scheduler::class.java)
        )
    }

    @Test
    fun testLaunchNextActivity() {
        val testScheduler = TestScheduler()
        val environment = environment.copy(
            scheduler = testScheduler
        )
        val vm = SplashScreenViewModel(environment)
        val launchNextActivity = TestSubscriber<Uri>()
        vm.outputs.launchNextActivity().subscribe(launchNextActivity::onNext)

        vm.inputs.fakeLoading()
        testScheduler.advanceTimeBy(1000, TimeUnit.MILLISECONDS)
        launchNextActivity.assertValue { it == Uri.parse(ApplicationMap.Home.FEED) }
    }

}