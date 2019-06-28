package io.freshdroid.mymonzo.feed

import io.freshdroid.mymonzo.core.user.CurrentUserType
import io.freshdroid.mymonzo.feed.network.ApiFeedType
import io.reactivex.Scheduler

data class FeedEnvironment(
    val currentUser: CurrentUserType,
    val apiFeed: ApiFeedType,
    val scheduler: Scheduler
)