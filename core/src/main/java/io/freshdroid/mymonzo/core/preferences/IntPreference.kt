package io.freshdroid.mymonzo.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class IntPreference @Inject constructor(
    private val _sharedPreferences: SharedPreferences,
    private val _key: String,
    private val _defaultValue: Int = 0
) : IntPreferenceType {

    override fun get(): Int {
        return _sharedPreferences.getInt(_key, _defaultValue)
    }

    override fun isSet(): Boolean {
        return _sharedPreferences.contains(_key)
    }

    override fun set(value: Int) {
        _sharedPreferences.edit().putInt(_key, value).apply()
    }

    override fun delete() {
        _sharedPreferences.edit().remove(_key).apply()
    }

}