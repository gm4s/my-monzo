package io.freshdroid.mymonzo.feed.di

import dagger.Component
import io.freshdroid.core.di.CoreComponent
import io.freshdroid.mymonzo.feed.FeedEnvironment

@FeedScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [FeedModule::class]
)
interface FeedComponent {

    fun environment(): FeedEnvironment

}