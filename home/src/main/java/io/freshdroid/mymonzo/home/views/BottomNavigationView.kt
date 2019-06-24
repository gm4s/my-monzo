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

    private val feedBottomNavigationItemView: BottomNavigationItemView by bind(R.id.feedBottomNavigationItemView)
    private val summaryBottomNavigationItemView: BottomNavigationItemView by bind(R.id.summaryBottomNavigationItemView)
    private val accountBottomNavigationItemView: BottomNavigationItemView by bind(R.id.accountBottomNavigationItemView)
    private val helpBottomNavigationItemView: BottomNavigationItemView by bind(R.id.helpBottomNavigationItemView)

    private var mListener: Listener? = null
    private var mCurrentState: FragmentState = FragmentState.FEED

    init {
        LayoutInflater.from(context).inflate(R.layout.bottom_navigation_view, this, true)

        feedBottomNavigationItemView.setOnClickListener { setStateActiveTab(FragmentState.FEED) }
        summaryBottomNavigationItemView.setOnClickListener { setStateActiveTab(FragmentState.SUMMARY) }
        accountBottomNavigationItemView.setOnClickListener { setStateActiveTab(FragmentState.ACCOUNT) }
        helpBottomNavigationItemView.setOnClickListener { setStateActiveTab(FragmentState.HELP) }
    }

    fun setListener(listener: Listener) {
        mListener = listener
    }

    fun getSelectedState(): FragmentState = mCurrentState

    fun setStateActiveTab(state: FragmentState) {
        when (state) {
            FragmentState.FEED -> {
                mCurrentState = FragmentState.FEED
                resetSate(state)
                feedBottomNavigationItemView.setActive(true)
                mListener?.onFeedClick()
            }
            FragmentState.SUMMARY -> {
                mCurrentState = FragmentState.SUMMARY
                resetSate(state)
                summaryBottomNavigationItemView.setActive(true)
                mListener?.onSummaryClick()
            }
            FragmentState.ACCOUNT -> {
                mCurrentState = FragmentState.ACCOUNT
                resetSate(state)
                accountBottomNavigationItemView.setActive(true)
                mListener?.onAccountClick()
            }
            FragmentState.HELP -> {
                mCurrentState = FragmentState.HELP
                resetSate(state)
                helpBottomNavigationItemView.setActive(true)
                mListener?.onHelpClick()
            }
        }
    }

    private fun resetSate(state: FragmentState) {
        when (state) {
            FragmentState.FEED -> {
                summaryBottomNavigationItemView.setActive(false)
                accountBottomNavigationItemView.setActive(false)
                helpBottomNavigationItemView.setActive(false)
            }
            FragmentState.SUMMARY -> {
                feedBottomNavigationItemView.setActive(false)
                accountBottomNavigationItemView.setActive(false)
                helpBottomNavigationItemView.setActive(false)
            }
            FragmentState.ACCOUNT -> {
                feedBottomNavigationItemView.setActive(false)
                summaryBottomNavigationItemView.setActive(false)
                helpBottomNavigationItemView.setActive(false)
            }
            FragmentState.HELP -> {
                feedBottomNavigationItemView.setActive(false)
                summaryBottomNavigationItemView.setActive(false)
                accountBottomNavigationItemView.setActive(false)
            }
        }
    }

}