package io.freshdroid.mymonzo.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class BooleanPreference @Inject constructor(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: Boolean = false
): BooleanPreferenceType {

    override fun get(): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun isSet(): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun set(value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun delete() {
        sharedPreferences.edit().remove(key).apply()
    }

}