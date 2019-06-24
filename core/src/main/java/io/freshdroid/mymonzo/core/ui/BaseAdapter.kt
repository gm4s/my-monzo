package io.freshdroid.mymonzo.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

abstract class BaseAdapter<T : Any> : RecyclerView.Adapter<BaseViewHolder>() {

    protected abstract fun layout(item: T): Int

    protected abstract fun viewHolder(@LayoutRes layout: Int, view: View): BaseViewHolder

    override fun onCreateViewHolder(viewGroup: ViewGroup, layout: Int): BaseViewHolder {
        val view = inflateView(viewGroup, layout)
        return viewHolder(layout, view)
    }

    override fun onBindViewHolder(viewHolder: BaseViewHolder, position: Int) {
        val data = objectFromPosition(position)

        try {
            viewHolder.bindData(data)
            viewHolder.onBind()
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun getItemViewType(position: Int): Int = layout(objectFromPosition(position))

    protected abstract fun objectFromPosition(position: Int): T

    protected abstract fun positionFromObject(item: T): Int

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return layoutInflater.inflate(viewType, viewGroup, false)
    }

}