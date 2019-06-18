package io.freshdroid.mymonzo.splashscreen

import io.freshdroid.core.user.CurrentUserType
import io.freshdroid.mymonzo.splashscreen.network.ApiSplashScreenType
import io.reactivex.Scheduler
import javax.inject.Inject

data class SplashScreenEnvironment @Inject constructor(
    val currentUser: CurrentUserType,
    val apiSplashScreen: ApiSplashScreenType,
    val scheduler: Scheduler
)