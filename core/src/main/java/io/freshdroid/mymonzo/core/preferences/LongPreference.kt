package io.freshdroid.mymonzo.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class LongPreference @Inject constructor(
    private val _sharedPreferences: SharedPreferences,
    private val _key: String,
    private val _defaultValue: Long = 0
) : LongPreferenceType {

    override fun get(): Long {
        return _sharedPreferences.getLong(_key, _defaultValue)
    }

    override fun isSet(): Boolean {
        return _sharedPreferences.contains(_key)
    }

    override fun set(value: Long) {
        _sharedPreferences.edit().putLong(_key, value).apply()
    }

    override fun delete() {
        _sharedPreferences.edit().remove(_key).apply()
    }

}