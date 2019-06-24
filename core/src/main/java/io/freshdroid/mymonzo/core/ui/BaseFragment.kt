package io.freshdroid.mymonzo.core.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import timber.log.Timber

open class BaseFragment : Fragment(), LifecycleOwner, LifecycleObserver {

    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    @CallSuper
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        Timber.v(this.toString(), "onActivityResult")
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        Timber.v(this.toString(), "onCreateView")
        return view
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.v(this.toString(), "onViewCreated")
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
    override fun onDestroyView() {
        super.onDestroyView()
        Timber.v(this.toString(), "onDestroyView")
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        Timber.v(this.toString(), "onDestroy")
    }

    @CallSuper
    override fun onDetach() {
        super.onDetach()
        Timber.v(this.toString(), "onDetach")
    }

}