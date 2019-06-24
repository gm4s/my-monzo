package io.freshdroid.mymonzo.splashscreen.di

import dagger.Component
import io.freshdroid.mymonzo.core.di.CoreComponent
import io.freshdroid.mymonzo.splashscreen.SplashScreenEnvironment

@SplashScreenScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [SplashScreenModule::class]
)
interface SplashScreenComponent {

    fun environment(): SplashScreenEnvironment

}