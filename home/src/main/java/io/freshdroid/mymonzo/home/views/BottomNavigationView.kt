package io.freshdroid.mymonzo.home.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import io.freshdroid.mymonzo.core.extensions.bind
import io.freshdroid.mymonzo.home.R

class BottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    interface Listener {
        fun onFeedClick()
        fun onSummaryClick()
        fun onAccountClick()
        fun onHelpClick()
    }

    private val _feedBottomNavigationItemView: BottomNavigationItemView by bind(R.id.feedBottomNavigationItemView)
    private val _summaryBottomNavigationItemView: BottomNavigationItemView by bind(R.id.summaryBottomNavigationItemView)
    private val _accountBottomNavigationItemView: BottomNavigationItemView by bind(R.id.accountBottomNavigationItemView)
    private val _helpBottomNavigationItemView: BottomNavigationItemView by bind(R.id.helpBottomNavigationItemView)

    private var _listener: Listener? = null
    private var _currentState: FragmentState = FragmentState.FEED

    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_view, this, true)

        _feedBottomNavigationItemView.setOnClickListener { setStateActiveTab(FragmentState.FEED) }
        _summaryBottomNavigationItemView.setOnClickListener { setStateActiveTab(FragmentState.SUMMARY) }
        _accountBottomNavigationItemView.setOnClickListener { setStateActiveTab(FragmentState.ACCOUNT) }
        _helpBottomNavigationItemView.setOnClickListener { setStateActiveTab(FragmentState.HELP) }
    }

    fun setListener(listener: Listener) {
        _listener = listener
    }

    fun getSelectedState(): FragmentState = _currentState

    fun setStateActiveTab(state: FragmentState) {
        when (state) {
            FragmentState.FEED -> {
                _currentState = FragmentState.FEED
                resetSate(state)
                _feedBottomNavigationItemView.setActive(true)
                _listener?.onFeedClick()
            }
            FragmentState.SUMMARY -> {
                _currentState = FragmentState.SUMMARY
                resetSate(state)
                _summaryBottomNavigationItemView.setActive(true)
                _listener?.onSummaryClick()
            }
            FragmentState.ACCOUNT -> {
                _currentState = FragmentState.ACCOUNT
                resetSate(state)
                _accountBottomNavigationItemView.setActive(true)
                _listener?.onAccountClick()
            }
            FragmentState.HELP -> {
                _currentState = FragmentState.HELP
                resetSate(state)
                _helpBottomNavigationItemView.setActive(true)
                _listener?.onHelpClick()
            }
        }
    }

    private fun resetSate(state: FragmentState) {
        when (state) {
            FragmentState.FEED -> {
                _summaryBottomNavigationItemView.setActive(false)
                _accountBottomNavigationItemView.setActive(false)
                _helpBottomNavigationItemView.setActive(false)
            }
            FragmentState.SUMMARY -> {
                _feedBottomNavigationItemView.setActive(false)
                _accountBottomNavigationItemView.setActive(false)
                _helpBottomNavigationItemView.setActive(false)
            }
            FragmentState.ACCOUNT -> {
                _feedBottomNavigationItemView.setActive(false)
                _summaryBottomNavigationItemView.setActive(false)
                _helpBottomNavigationItemView.setActive(false)
            }
            FragmentState.HELP -> {
                _feedBottomNavigationItemView.setActive(false)
                _summaryBottomNavigationItemView.setActive(false)
                _accountBottomNavigationItemView.setActive(false)
            }
        }
    }

}