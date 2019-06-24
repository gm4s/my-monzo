package io.freshdroid.mymonzo.core.extensions

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat

fun <T : View> View.bind(@IdRes res: Int): Lazy<T> {
    return unsafeLazy { findViewById<T>(res) }
}

fun View.getCompatDrawable(@DrawableRes res: Int): Drawable? {
    return ContextCompat.getDrawable(context, res)
}

private fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)