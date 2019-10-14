package io.freshdroid.mymonzo.home.viewmodels

import android.content.Intent
import io.freshdroid.mymonzo.home.MyMonzoRobolectricTestCase
import io.freshdroid.mymonzo.home.views.FragmentState
import io.freshdroid.mymonzo.navigation.Activities
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

internal class HomeViewModelTest : MyMonzoRobolectricTestCase() {

    @Test
    fun testDisplaySelectedFragment_feed() {
        val vm = HomeViewModel()
        val displaySelectedFragment = TestSubscriber<FragmentState>()
        vm.outputs.displaySelectedFragment().subscribe(displaySelectedFragment::onNext)

        vm.inputs.onFeedClick()
        displaySelectedFragment.assertValue { it == FragmentState.FEED }
    }

    @Test
    fun testDisplaySelectedFragment_summary() {
        val vm = HomeViewModel()
        val displaySelectedFragment = TestSubscriber<FragmentState>()
        vm.outputs.displaySelectedFragment().subscribe(displaySelectedFragment::onNext)

        vm.inputs.onSummaryClick()
        displaySelectedFragment.assertValue { it == FragmentState.SUMMARY }
    }

    @Test
    fun testDisplaySelectedFragment_account() {
        val vm = HomeViewModel()
        val displaySelectedFragment = TestSubscriber<FragmentState>()
        vm.outputs.displaySelectedFragment().subscribe(displaySelectedFragment::onNext)

        vm.inputs.onAccountClick()
        displaySelectedFragment.assertValue { it == FragmentState.ACCOUNT }
    }

    @Test
    fun testDisplaySelectedFragment_help() {
        val vm = HomeViewModel()
        val displaySelectedFragment = TestSubscriber<FragmentState>()
        vm.outputs.displaySelectedFragment().subscribe(displaySelectedFragment::onNext)

        vm.inputs.onHelpClick()
        displaySelectedFragment.assertValue { it == FragmentState.HELP }
    }

    @Test
    fun testInitBottomNavigationBar_feed() {
        val vm = HomeViewModel()
        val initBottomNavigationBar = TestSubscriber<FragmentState>()
        vm.outputs.initBottomNavigationBar().subscribe(initBottomNavigationBar::onNext)

        val homeIntent = Intent()
        homeIntent.putExtra(Activities.Home.KEY_EXTRA_HOME_ACTIVE_TAB, FragmentState.FEED.tag)
        vm.intent(homeIntent)

        initBottomNavigationBar.assertValue { it == FragmentState.FEED }
    }

    @Test
    fun testInitBottomNavigationBar_summary() {
        val vm = HomeViewModel()
        val initBottomNavigationBar = TestSubscriber<FragmentState>()
        vm.outputs.initBottomNavigationBar().subscribe(initBottomNavigationBar::onNext)

        val homeIntent = Intent()
        homeIntent.putExtra(Activities.Home.KEY_EXTRA_HOME_ACTIVE_TAB, FragmentState.SUMMARY.tag)
        vm.intent(homeIntent)

        initBottomNavigationBar.assertValue { it == FragmentState.SUMMARY }
    }

    @Test
    fun testInitBottomNavigationBar_account() {
        val vm = HomeViewModel()
        val initBottomNavigationBar = TestSubscriber<FragmentState>()
        vm.outputs.initBottomNavigationBar().subscribe(initBottomNavigationBar::onNext)

        val homeIntent = Intent()
        homeIntent.putExtra(Activities.Home.KEY_EXTRA_HOME_ACTIVE_TAB, FragmentState.ACCOUNT.tag)
        vm.intent(homeIntent)

        initBottomNavigationBar.assertValue { it == FragmentState.ACCOUNT }
    }

    @Test
    fun testInitBottomNavigationBar_help() {
        val vm = HomeViewModel()
        val initBottomNavigationBar = TestSubscriber<FragmentState>()
        vm.outputs.initBottomNavigationBar().subscribe(initBottomNavigationBar::onNext)

        val homeIntent = Intent()
        homeIntent.putExtra(Activities.Home.KEY_EXTRA_HOME_ACTIVE_TAB, FragmentState.HELP.tag)
        vm.intent(homeIntent)

        initBottomNavigationBar.assertValue { it == FragmentState.HELP }
    }

}