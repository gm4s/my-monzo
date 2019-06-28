package io.freshdroid.mymonzo.feed.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.uber.autodispose.autoDisposable
import io.freshdroid.mymonzo.core.extensions.showToastApiError
import io.freshdroid.mymonzo.core.rx.transformers.Transformers.observeForUI
import io.freshdroid.mymonzo.core.ui.BaseFragment
import io.freshdroid.mymonzo.coreComponent
import io.freshdroid.mymonzo.feed.R
import io.freshdroid.mymonzo.feed.di.FeedComponentManager
import io.freshdroid.mymonzo.feed.models.Balance
import io.freshdroid.mymonzo.feed.viewmodels.FeedFragmentViewModel
import kotlinx.android.synthetic.main.fragment_feed.*
import java.text.NumberFormat
import java.util.*

class FeedFragment : BaseFragment() {

    private val _component by lazy {
        FeedComponentManager.feedComponent(coreComponent())
    }
    private val _viewModelFactory by lazy {
        FeedFragmentViewModel.Factory(_component.environment(), scopeProvider)
    }
    private val _viewModel by lazy {
        ViewModelProviders.of(this, _viewModelFactory).get(FeedFragmentViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _viewModel.outputs.currentBalance()
            .compose(observeForUI())
            .autoDisposable(scopeProvider)
            .subscribe(this::displayBalanceNow)

        _viewModel.errors.fetchBalanceApiError()
            .compose(observeForUI())
            .autoDisposable(scopeProvider)
            .subscribe { this.showToastApiError(it) }

        _viewModel.arguments(arguments)
        _viewModel.inputs.fetchCurrentBalance()
    }

    private fun displayBalanceNow(balance: Balance) {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        numberFormat.currency = balance.currency
        balanceNowTextView.text = numberFormat.format((balance.now / 100))
    }

}