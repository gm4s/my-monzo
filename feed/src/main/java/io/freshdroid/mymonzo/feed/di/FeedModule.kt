package io.freshdroid.mymonzo.feed.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.freshdroid.mymonzo.core.network.HttpTransitionFactoryType
import io.freshdroid.mymonzo.core.user.CurrentUserType
import io.freshdroid.mymonzo.feed.FeedEnvironment
import io.freshdroid.mymonzo.feed.network.ApiFeed
import io.freshdroid.mymonzo.feed.network.ApiFeedType
import io.reactivex.Scheduler

@Module
class FeedModule {

    @Provides
    @FeedScope
    fun provideFeedEnvironment(
        currentUser: CurrentUserType,
        apiFeed: ApiFeedType,
        scheduler: Scheduler
    ): FeedEnvironment {
        return FeedEnvironment(
            currentUser,
            apiFeed,
            scheduler
        )
    }

    @Provides
    @FeedScope
    fun provideApiFeedType(
        httpTransitionFactory: HttpTransitionFactoryType,
        moshi: Moshi
    ): ApiFeedType {
        return ApiFeed(
            httpTransitionFactory,
            moshi
        )
    }

}