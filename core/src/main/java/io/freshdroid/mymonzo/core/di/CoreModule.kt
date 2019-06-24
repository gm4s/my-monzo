package io.freshdroid.mymonzo.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.Resources
import android.preference.PreferenceManager
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.freshdroid.mymonzo.core.preferences.StringPreferenceType
import io.freshdroid.mymonzo.core.qualifiers.AccessTokenPreference
import io.freshdroid.mymonzo.core.qualifiers.UserPreference
import io.freshdroid.mymonzo.core.rx.RxBus
import io.freshdroid.mymonzo.core.rx.RxBusType
import io.freshdroid.mymonzo.core.user.CurrentUser
import io.freshdroid.mymonzo.core.user.CurrentUserType
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        PreferencesModule::class
    ]
)
class CoreModule(
    private val context: Context
) {

    private val application = context as Application

    @Provides
    fun provideContext(): Context = context

    @Provides
    fun provideApplication(): Application = application

    @Provides
    fun provideResources(context: Context): Resources = context.resources

    @Provides
    @Singleton
    fun providePackageInfo(application: Application): PackageInfo {
        try {
            return application.packageManager.getPackageInfo(application.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            throw RuntimeException()
        }
    }

    @Provides
    @Singleton
    fun provideLocale(): Locale = Locale.getDefault()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.application)

    @Provides
    @Singleton
    fun provideScheduler(): Scheduler = Schedulers.computation()

    @Provides
    @Singleton
    fun provideCurrentUserType(
        moshi: Moshi,
        @AccessTokenPreference accessTokenPreference: StringPreferenceType,
        @UserPreference userPreference: StringPreferenceType
    ): CurrentUserType {
        return CurrentUser(
            moshi,
            accessTokenPreference,
            userPreference
        )
    }

    @Provides
    @Singleton
    fun provideRxBusType(): RxBusType {
        return RxBus()
    }

}