package io.freshdroid.mymonzo.core.ui

import android.app.Activity
import android.content.Intent

data class ActivityResult(val requestCode: Int, val resultCode: Int, val intent: Intent?) {

    companion object {
        @JvmStatic
        fun create(requestCode: Int, resultCode: Int, intent: Intent?): ActivityResult = ActivityResult(requestCode, resultCode, intent)
    }

    fun isCanceled(): Boolean = resultCode == Activity.RESULT_CANCELED

    fun isOk(): Boolean = resultCode == Activity.RESULT_OK

    fun isRequestCode(v: Int): Boolean = requestCode == v

}