/*
 * Copyright 2017 Kickstarter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ***
 *
 * Original: https://github.com/kickstarter/android-oss/blob/master/app/src/main/java/com/kickstarter/libs/RecyclerViewPaginator.java
 * Modifications: Some modifiers and annotations have been added by Pickable App.
 */
package io.freshdroid.mymonzo.core.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action

const val DIRECTION_DOWN = 1

class RecyclerViewPaginator(
        private val recyclerView: RecyclerView,
        private val fetchNext: Action
) {

    private var subscription: Disposable? = null

    init {
        start()
    }

    private fun start() {
        stop()

        subscription = RxRecyclerView.scrollEvents(recyclerView)
                .filter { recyclerView.canScrollVertically(DIRECTION_DOWN) }
                .map { recyclerView.layoutManager }
                .ofType(LinearLayoutManager::class.java)
                .map(this::displayedItemFromLinearLayout)
                .filter { item -> item.second != 0 }
                .filter(this::visibleItemIsCloseToBottom)
                .distinctUntilChanged()
                .subscribe { fetchNext.run() }
    }

    fun stop() {
        if (subscription != null) {
            subscription?.dispose()
            subscription = null
        }
    }

    /**
     * Returns a (visibleItem, totalItemCount) pair given a linear layout manager.
     */
    private fun displayedItemFromLinearLayout(manager: LinearLayoutManager): Pair<Int, Int> {
        return Pair(manager.findLastVisibleItemPosition(), manager.itemCount)
    }

    private fun visibleItemIsCloseToBottom(visibleItemOfTotal: Pair<Int, Int>): Boolean {
        return visibleItemOfTotal.first == visibleItemOfTotal.second - 1
    }

}