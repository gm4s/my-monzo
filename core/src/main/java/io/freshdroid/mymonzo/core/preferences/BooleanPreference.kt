package io.freshdroid.mymonzo.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class BooleanPreference @Inject constructor(
    private val _sharedPreferences: SharedPreferences,
    private val _key: String,
    private val _defaultValue: Boolean = false
): BooleanPreferenceType {

    override fun get(): Boolean {
        return _sharedPreferences.getBoolean(_key, _defaultValue)
    }

    override fun isSet(): Boolean {
        return _sharedPreferences.contains(_key)
    }

    override fun set(value: Boolean) {
        _sharedPreferences.edit().putBoolean(_key, value).apply()
    }

    override fun delete() {
        _sharedPreferences.edit().remove(_key).apply()
    }

}