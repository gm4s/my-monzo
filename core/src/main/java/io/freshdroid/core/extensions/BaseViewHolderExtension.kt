@file:JvmName("BaseViewHolderExtension")

package io.freshdroid.core.extensions

import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import io.freshdroid.core.ui.BaseViewHolder

fun <T : View> BaseViewHolder.bind(@IdRes res: Int): Lazy<T> {
    return itemView.bind(res)
}

fun BaseViewHolder.getCompatDrawable(@DrawableRes res: Int): Drawable? {
    return itemView.getCompatDrawable(res)
}