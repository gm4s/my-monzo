package io.freshdroid.mymonzo.splashscreen.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.freshdroid.mymonzo.core.network.HttpTransitionFactoryType
import io.freshdroid.mymonzo.core.user.CurrentUserType
import io.freshdroid.mymonzo.splashscreen.SplashScreenEnvironment
import io.freshdroid.mymonzo.splashscreen.network.ApiSplashScreen
import io.freshdroid.mymonzo.splashscreen.network.ApiSplashScreenType
import io.reactivex.Scheduler

@Module
class SplashScreenModule {

    @Provides
    @SplashScreenScope
    fun provideSplashScreenEnvironment(
        currentUser: CurrentUserType,
        apiSplashScreen: ApiSplashScreenType,
        scheduler: Scheduler
    ): SplashScreenEnvironment {
        return SplashScreenEnvironment(
            currentUser,
            apiSplashScreen,
            scheduler
        )
    }

    @Provides
    @SplashScreenScope
    fun provideApiSplashScreenType(
        httpTransitionFactory: HttpTransitionFactoryType,
        moshi: Moshi
    ): ApiSplashScreenType {
        return ApiSplashScreen(
            httpTransitionFactory,
            moshi
        )
    }

}