package io.freshdroid.mymonzo.core.predicates

import android.os.Looper

internal object ThreadPredicate {

    fun isMainThread(): Boolean {
        return Looper.getMainLooper().thread == Thread.currentThread()
    }

}