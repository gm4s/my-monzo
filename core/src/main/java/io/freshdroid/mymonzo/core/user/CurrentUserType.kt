package io.freshdroid.mymonzo.core.user

import io.reactivex.Observable

abstract class CurrentUserType {

    abstract fun refresh(freshUser: User)

    abstract fun toObservable(): Observable<User>

    abstract fun setAccessToken(accessToken: String)

    abstract fun getAccessToken(): String?

    abstract fun isLoggedIn(): Boolean

    abstract fun logout()

}