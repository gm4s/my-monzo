package io.freshdroid.mymonzo.home.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.uber.autodispose.autoDisposable
import io.freshdroid.mymonzo.core.rx.transformers.Transformers.observeForUI
import io.freshdroid.mymonzo.core.ui.BaseActivity
import io.freshdroid.mymonzo.home.R
import io.freshdroid.mymonzo.home.viewmodels.HomeViewModel
import io.freshdroid.mymonzo.navigation.Fragments
import io.freshdroid.mymonzo.navigation.fragmentTo
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    private val _viewModelFactory by lazy {
        HomeViewModel.Factory(scopeProvider)
    }
    private val _viewModel by lazy {
        ViewModelProviders.of(this, _viewModelFactory).get(HomeViewModel::class.java)
    }

    private lateinit var _feedFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        _viewModel.outputs.initBottomNavigationBar()
            .compose(observeForUI())
            .autoDisposable(scopeProvider)
            .subscribe(homeBottomNavigationView::setStateActiveTab)

        _viewModel.outputs.displaySelectedFragment()
            .compose(observeForUI())
            .autoDisposable(scopeProvider)
            .subscribe(this::displaySelectedFragment)

        initFragments()

        _viewModel.intent(intent)
        homeBottomNavigationView.setListener(_viewModel.inputs)
    }

    private fun initFragments() {
        _feedFragment = fragmentTo(this, Fragments.Home.Feed)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerFrameLayout, _feedFragment).commit()
    }

    private fun displaySelectedFragment(state: FragmentState) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (state) {
            FragmentState.FEED -> {
                fragmentTransaction.show(_feedFragment).commit()
            }
            else -> fragmentTransaction.hide(_feedFragment).commit()
        }
    }

}

enum class FragmentState(
    val tag: String
) {
    FEED("tab_feed"),
    SUMMARY("tab_summary"),
    ACCOUNT("tab_account"),
    HELP("tab_help");

    companion object {
        @JvmStatic
        fun stateFromTag(tag: String): FragmentState {
            return when (tag) {
                "tab_feed" -> FEED
                "tab_summary" -> SUMMARY
                "tab_account" -> ACCOUNT
                else -> HELP
            }
        }
    }
}