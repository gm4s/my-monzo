package io.freshdroid.mymonzo.feed.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import io.freshdroid.core.viewmodel.FragmentViewModel
import io.freshdroid.mymonzo.feed.FeedEnvironment

class FeedFragmentViewModel(
    environment: FeedEnvironment,
    scopeProvider: AndroidLifecycleScopeProvider
) : FragmentViewModel() {

    init {

    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
        private val environment: FeedEnvironment,
        private val scopeProvider: AndroidLifecycleScopeProvider
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FeedFragmentViewModel(environment, scopeProvider) as T
        }
    }

}