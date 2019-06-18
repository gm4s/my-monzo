package io.freshdroid.core.di

import android.content.Context
import com.moczul.ok2curl.CurlInterceptor
import com.moczul.ok2curl.logger.Loggable
import com.readystatesoftware.chuck.ChuckInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.freshdroid.core.BuildConfig
import io.freshdroid.core.network.*
import io.freshdroid.core.network.interceptors.ApiMonzoRequestInterceptor
import io.freshdroid.core.user.CurrentUserType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    internal fun provideApiMonzoRequestInterceptor(
        currentUser: CurrentUserType,
        locale: Locale
    ): ApiMonzoRequestInterceptor {
        return ApiMonzoRequestInterceptor(locale, currentUser)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        context: Context,
        apiMonzoRequestInterceptor: ApiMonzoRequestInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        val client = OkHttpClient.Builder()
        client.addInterceptor(apiMonzoRequestInterceptor)
        client.pingInterval(20, TimeUnit.SECONDS)

        val protocols = arrayListOf(
            Protocol.HTTP_2,
            Protocol.HTTP_1_1
        )
        client.protocols(protocols)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(loggingInterceptor)
            client.addInterceptor(ChuckInterceptor(context))
            client.addInterceptor(CurlInterceptor(Loggable { Timber.d("D/OkHttp: $it") }))
        }

        return client.build()
    }

    @Provides
    @Singleton
    fun provideApiRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return createRetrofit(ApiEndpoint.MONZO.url, okHttpClient)
    }

    private fun createRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(StringConverterFactory())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHttpTransitionFactoryType(apiService: ApiService): HttpTransitionFactoryType {
        return HttpTransitionFactory(apiService)
    }

}