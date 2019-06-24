package io.freshdroid.mymonzo.core.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.freshdroid.mymonzo.core.preferences.SharedPreferenceKey
import io.freshdroid.mymonzo.core.preferences.StringPreference
import io.freshdroid.mymonzo.core.preferences.StringPreferenceType
import io.freshdroid.mymonzo.core.qualifiers.AccessTokenPreference
import io.freshdroid.mymonzo.core.qualifiers.UserPreference
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