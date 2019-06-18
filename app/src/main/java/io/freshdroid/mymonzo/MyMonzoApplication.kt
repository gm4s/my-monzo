package io.freshdroid.mymonzo

import android.app.NotificationManager
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDexApplication
import io.freshdroid.core.di.CoreComponent
import io.freshdroid.core.di.CoreModule
import io.freshdroid.core.di.DaggerCoreComponent
import io.freshdroid.core.ui.BaseActivity
import io.freshdroid.core.ui.BaseFragment
import timber.log.Timber

class MyMonzoApplication : MultiDexApplication(), LifecycleObserver {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .coreModule(CoreModule(this))
            .build()
    }

    var isAppOnForeground = false

    companion object {
        @JvmStatic
        fun coreComponent(context: Context) = (context.applicationContext as MyMonzoApplication).coreComponent
    }

    fun coreComponent(): CoreComponent = coreComponent

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForegrounded() {
        isAppOnForeground = true
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgrounded() {
        isAppOnForeground = false
    }

}

fun BaseActivity.coreComponent() = MyMonzoApplication.coreComponent(this)

fun BaseFragment.coreComponent() = MyMonzoApplication.coreComponent(requireNotNull(this.context))