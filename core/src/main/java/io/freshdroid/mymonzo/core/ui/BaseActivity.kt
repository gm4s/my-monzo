package io.freshdroid.mymonzo.core.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.uber.autodispose.autoDisposable
import io.freshdroid.mymonzo.core.rx.Irrelevant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

open class BaseActivity : AppCompatActivity(), LifecycleOwner, LifecycleObserver {

    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    private val _back = PublishSubject.create<Irrelevant>()

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        Timber.v(this.toString(), "onActivityResult")
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v(this.toString(), "onCreate")
    }

    @CallSuper
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Timber.v(this.toString(), "onNewIntent")
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        Timber.v(this.toString(), "onStart")

        _back
            .observeOn(AndroidSchedulers.mainThread())
            .autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_STOP))
            .subscribe { goBack() }
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        Timber.v(this.toString(), "onResume")
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        Timber.v(this.toString(), "onPause")
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        Timber.v(this.toString(), "onStop")
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        Timber.v(this.toString(), "onDestroy")
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.v(this.toString(), "onSaveInstanceState")
    }

    @CallSuper
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    @CallSuper
    override fun onBackPressed() {
        back()
    }

    @CallSuper
    override fun finish() {
        super.finish()
        val exitTransitions = exitTransition()
        if (exitTransitions != null) {
            overridePendingTransition(exitTransitions.first, exitTransitions.second)
        }
    }

    protected open fun exitTransition(): Pair<Int, Int>? {
        return null
    }

    protected fun back() {
        _back.onNext(Irrelevant.INSTANCE)
    }

    private fun goBack() {
        super.onBackPressed()
        val exitTransitions = exitTransition()
        if (exitTransitions != null) {
            overridePendingTransition(exitTransitions.first, exitTransitions.second)
        }
    }

}