package io.freshdroid.mymonzo.feed.di

import dagger.Module
import dagger.Provides
import io.freshdroid.mymonzo.core.user.CurrentUserType
import io.freshdroid.mymonzo.feed.FeedEnvironment
import io.reactivex.Scheduler

@Module
class FeedModule {

    @Provides
    @FeedScope
    fun provideFeedEnvironment(
        currentUser: CurrentUserType,
        scheduler: Scheduler
    ): FeedEnvironment {
        return FeedEnvironment(
            currentUser,
            scheduler
        )
    }

}