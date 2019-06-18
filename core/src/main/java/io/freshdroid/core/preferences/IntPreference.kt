package io.freshdroid.core.preferences

import android.content.SharedPreferences
import javax.inject.Inject

class IntPreference @Inject constructor(
        private val sharedPreferences: SharedPreferences,
        private val key: String,
        private val defaultValue: Int = 0
) : IntPreferenceType {

    override fun get(): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun isSet(): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun set(value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun delete() {
        sharedPreferences.edit().remove(key).apply()
    }

}