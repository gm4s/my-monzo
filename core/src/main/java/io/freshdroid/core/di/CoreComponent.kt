package io.freshdroid.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import dagger.Component
import io.freshdroid.core.network.HttpTransitionFactoryType
import io.freshdroid.core.rx.RxBusType
import io.freshdroid.core.user.CurrentUserType
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = [CoreModule::class])
interface CoreComponent {

    fun application(): Application
    fun context(): Context
    fun sharedPreferences(): SharedPreferences
    fun currentUser(): CurrentUserType
    fun httpTransitionFactory(): HttpTransitionFactoryType
    fun okHttpClient(): OkHttpClient
    fun moshi(): Moshi
    fun scheduler(): Scheduler
    fun rxBus(): RxBusType

}