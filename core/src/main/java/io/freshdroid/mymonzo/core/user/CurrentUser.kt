package io.freshdroid.mymonzo.core.user

import android.annotation.SuppressLint
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import io.freshdroid.mymonzo.core.preferences.StringPreferenceType
import io.freshdroid.mymonzo.core.qualifiers.AccessTokenPreference
import io.freshdroid.mymonzo.core.qualifiers.UserPreference
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@SuppressLint("CheckResult")
internal class CurrentUser @Inject constructor(
    private val _moshi: Moshi,
    @UserPreference private val _userMePreference: StringPreferenceType,
    @AccessTokenPreference private val _accessTokenPreference: StringPreferenceType
) : CurrentUserType() {

    private val _userMe = BehaviorSubject.create<User>()

    init {
        val type = Types.newParameterizedType(User::class.java)

        _userMe
            .subscribe { freshUser ->
                val json = _moshi.adapter<User>(type).toJson(freshUser)
                _userMePreference.set(json)
            }

        _userMePreference.get()?.let { userPref ->
            _moshi.adapter<User>(type).fromJson(userPref)?.let { _userMe.onNext(it) }
        }
    }

    override fun refresh(freshUser: User) {
        _userMe.onNext(freshUser)
    }

    override fun toObservable(): Observable<User> {
        return _userMe
    }

    override fun setAccessToken(accessToken: String) {
        _accessTokenPreference.set(accessToken)
    }

    override fun getAccessToken(): String? {
        return _accessTokenPreference.get()
    }

    override fun isLoggedIn(): Boolean {
        return _accessTokenPreference.get() != null
    }

    override fun logout() {
        _accessTokenPreference.delete()
        _userMePreference.delete()
        _userMe.onNext(User())
    }

}