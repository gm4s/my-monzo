package io.freshdroid.core.user

import android.annotation.SuppressLint
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.freshdroid.core.preferences.StringPreferenceType
import io.freshdroid.core.qualifiers.AccessTokenPreference
import io.freshdroid.core.qualifiers.UserPreference
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@SuppressLint("CheckResult")
internal class CurrentUser @Inject constructor(
    private val moshi: Moshi,
    @UserPreference private val userMePreference: StringPreferenceType,
    @AccessTokenPreference private val accessTokenPreference: StringPreferenceType
) : CurrentUserType() {

    private val mUserMe = BehaviorSubject.create<User>()

    init {
        val type = Types.newParameterizedType(User::class.java)

        mUserMe
            .subscribe { freshUser ->
                val json = moshi.adapter<User>(type).toJson(freshUser)
                userMePreference.set(json)
            }

        userMePreference.get()?.let { userPref ->
            moshi.adapter<User>(type).fromJson(userPref)?.let { mUserMe.onNext(it) }
        }
    }

    override fun refresh(freshUser: User) {
        mUserMe.onNext(freshUser)
    }

    override fun toObservable(): Observable<User> {
        return mUserMe
    }

    override fun setAccessToken(accessToken: String) {
        accessTokenPreference.set(accessToken)
    }

    override fun getAccessToken(): String? {
        return accessTokenPreference.get()
    }

    override fun isLoggedIn(): Boolean {
        return accessTokenPreference.get() != null
    }

    override fun logout() {
        accessTokenPreference.delete()
        userMePreference.delete()
        mUserMe.onNext(User())
    }

}