package io.freshdroid.mymonzo.splashscreen.di

import dagger.Module
import dagger.Provides
import io.freshdroid.mymonzo.core.user.CurrentUserType
import io.freshdroid.mymonzo.splashscreen.SplashScreenEnvironment
import io.reactivex.Scheduler

@Module
class SplashScreenModule {

    @Provides
    @SplashScreenScope
    fun provideSplashScreenEnvironment(
        currentUser: CurrentUserType,
        scheduler: Scheduler
    ): SplashScreenEnvironment {
        return SplashScreenEnvironment(
            currentUser,
            scheduler
        )
    }

}