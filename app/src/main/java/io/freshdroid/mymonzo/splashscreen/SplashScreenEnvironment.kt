package io.freshdroid.mymonzo.splashscreen

import io.freshdroid.mymonzo.core.user.CurrentUserType
import io.reactivex.Scheduler
import javax.inject.Inject

data class SplashScreenEnvironment @Inject constructor(
    val currentUser: CurrentUserType,
    val scheduler: Scheduler
)