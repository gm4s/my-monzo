package io.freshdroid.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class LongPreference @Inject constructor(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: Long = 0
) : LongPreferenceType {

    override fun get(): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun isSet(): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun set(value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    override fun delete() {
        sharedPreferences.edit().remove(key).apply()
    }

}