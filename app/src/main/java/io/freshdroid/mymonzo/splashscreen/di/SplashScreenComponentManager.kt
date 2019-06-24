package io.freshdroid.mymonzo.splashscreen.di

import io.freshdroid.mymonzo.core.di.CoreComponent
import javax.inject.Singleton

@Singleton
object SplashScreenComponentManager {

    private var splashScreenComponent: SplashScreenComponent? = null

    fun splashScreenComponent(coreComponent: CoreComponent): SplashScreenComponent {
        if (splashScreenComponent == null)
            splashScreenComponent = DaggerSplashScreenComponent.builder().coreComponent(coreComponent).build()
        return splashScreenComponent as SplashScreenComponent
    }

    fun destroySplashScreenComponent() {
        splashScreenComponent = null
    }

}