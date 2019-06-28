package io.freshdroid.mymonzo.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class StringPreference @Inject constructor(
    private val _sharedPreferences: SharedPreferences,
    private val _key: String,
    private val _defaultValue: String? = null
) : StringPreferenceType {

    override fun get(): String? {
        return _sharedPreferences.getString(_key, _defaultValue)
    }

    override fun isSet(): Boolean {
        return _sharedPreferences.contains(_key)
    }

    override fun set(value: String) {
        _sharedPreferences.edit().putString(_key, value).apply()
    }

    override fun delete() {
        _sharedPreferences.edit().remove(_key).apply()
    }

}