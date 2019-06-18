package io.freshdroid.core.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.freshdroid.core.preferences.SharedPreferenceKey
import io.freshdroid.core.preferences.StringPreference
import io.freshdroid.core.preferences.StringPreferenceType
import io.freshdroid.core.qualifiers.AccessTokenPreference
import io.freshdroid.core.qualifiers.UserPreference
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Provides
    @Singleton
    @UserPreference
    fun provideUserPreference(sharedPreferences: SharedPreferences): StringPreferenceType {
        return StringPreference(
            sharedPreferences,
            SharedPreferenceKey.USER
        )
    }

    @Provides
    @Singleton
    @AccessTokenPreference
    fun provideAccessTokenPreference(sharedPreferences: SharedPreferences): StringPreferenceType {
        return StringPreference(
            sharedPreferences,
            SharedPreferenceKey.ACCESS_TOKEN
        )
    }

}