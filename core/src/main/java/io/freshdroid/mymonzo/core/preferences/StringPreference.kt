package io.freshdroid.mymonzo.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class StringPreference @Inject constructor(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: String? = null
) : StringPreferenceType {

    override fun get(): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    override fun isSet(): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun set(value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun delete() {
        sharedPreferences.edit().remove(key).apply()
    }

}