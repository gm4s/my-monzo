package io.freshdroid.mymonzo.core.ui

abstract class BaseArrayAdapter<T : Any> : BaseAdapter<T>() {

    private var _items = ArrayList<T>()

    fun items(): ArrayList<T> = this._items

    fun clearItems() {
        this._items.clear()
    }

    fun addItem(item: T) {
        this._items.add(item)
    }

    fun addItems(items: ArrayList<T>) {
        this._items.addAll(items)
    }

    fun setItems(items: ArrayList<T>) {
        this._items = items
    }

    fun insertItem(position: Int, item: T) {
        this._items.add(position, item)
    }

    fun deleteItem(item: T) {
        this._items.remove(item)
    }

    fun deleteAtPositionItem(position: Int): T {
        return this._items.removeAt(position)
    }

    fun setItem(location: Int, item: T) {
        this._items[location] = item
    }

    fun contains(item: T): Boolean {
        return this._items.contains(item)
    }

    override fun getItemCount(): Int = _items.size

    override fun objectFromPosition(position: Int): T = _items[position]

    override fun positionFromObject(item: T): Int = _items.indexOf(item)

}