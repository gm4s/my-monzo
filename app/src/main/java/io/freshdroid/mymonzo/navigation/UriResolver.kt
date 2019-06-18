package io.freshdroid.mymonzo.navigation

import android.content.Intent
import android.net.Uri

object UriResolver {

    @JvmStatic
    @Throws(IntentNotFoundException::class)
    fun resolve(uri: Uri): Intent = getIntent(uri)

    @Throws(IntentNotFoundException::class)
    private fun getIntent(uri: Uri): Intent {
        return when (uri.toString()) {
            ApplicationMap.Home.FEED -> getIntentHomeFeed()
            else -> throw IntentNotFoundException(uri)
        }
    }

    private fun getIntentHomeFeed(): Intent {
        return intentTo(Activities.Home.Feed)
            .putExtra(Activities.Home.KEY_EXTRA_HOME_ACTIVE_TAB, "tab_feed")
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
    }

}