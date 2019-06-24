package io.freshdroid.mymonzo.feed.di

import io.freshdroid.mymonzo.core.di.CoreComponent
import javax.inject.Singleton

@Singleton
object FeedComponentManager {

    private var splashScreenComponent: FeedComponent? = null

    fun feedComponent(coreComponent: CoreComponent): FeedComponent {
        if (splashScreenComponent == null)
            splashScreenComponent = DaggerFeedComponent.builder().coreComponent(coreComponent).build()
        return splashScreenComponent as FeedComponent
    }

    fun destroyFeedComponent() {
        splashScreenComponent = null
    }


}