package io.freshdroid.mymonzo.feed

import io.freshdroid.core.user.CurrentUserType
import io.reactivex.Scheduler

data class FeedEnvironment(
    val currentUser: CurrentUserType,
    val scheduler: Scheduler
)