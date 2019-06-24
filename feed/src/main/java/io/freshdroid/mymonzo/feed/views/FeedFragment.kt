package io.freshdroid.mymonzo.feed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import io.freshdroid.mymonzo.core.ui.BaseFragment
import io.freshdroid.mymonzo.coreComponent
import io.freshdroid.mymonzo.feed.R
import io.freshdroid.mymonzo.feed.di.FeedComponentManager
import io.freshdroid.mymonzo.feed.viewmodels.FeedFragmentViewModel

class FeedFragment : BaseFragment() {

    private val component by lazy {
        FeedComponentManager.feedComponent(coreComponent())
    }
    private val viewModelFactory by lazy {
        FeedFragmentViewModel.Factory(component.environment(), scopeProvider)
    }
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(FeedFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.arguments(arguments)
    }

}