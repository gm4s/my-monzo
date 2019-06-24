package io.freshdroid.mymonzo.core.ui

abstract class BaseArrayAdapter<T : Any> : BaseAdapter<T>() {

    private var items = ArrayList<T>()

    fun items(): ArrayList<T> = this.items

    fun clearItems() {
        this.items.clear()
    }

    fun addItem(item: T) {
        this.items.add(item)
    }

    fun addItems(items: ArrayList<T>) {
        this.items.addAll(items)
    }

    fun setItems(items: ArrayList<T>) {
        this.items = items
    }

    fun insertItem(position: Int, item: T) {
        this.items.add(position, item)
    }

    fun deleteItem(item: T) {
        this.items.remove(item)
    }

    fun deleteAtPositionItem(position: Int): T {
        return this.items.removeAt(position)
    }

    fun setItem(location: Int, item: T) {
        this.items[location] = item
    }

    fun contains(item: T): Boolean {
        return this.items.contains(item)
    }

    override fun getItemCount(): Int = items.size

    override fun objectFromPosition(position: Int): T = items[position]

    override fun positionFromObject(item: T): Int = items.indexOf(item)

}