@file:JvmName("ActivityHelper")

package io.freshdroid.mymonzo.navigation

import android.content.Intent

private const val PACKAGE_NAME = "io.freshdroid.mymonzo"

fun intentTo(addressableActivity: AddressableActivity): Intent {
    return Intent(Intent.ACTION_VIEW).setClassName(
        PACKAGE_NAME,
        addressableActivity.className
    )
}

interface AddressableActivity {
    val className: String
}

object Activities {

    object Home {

        const val KEY_EXTRA_HOME_ACTIVE_TAB = "KEY_EXTRA_HOME_ACTIVE_TAB"

        object Feed : AddressableActivity {
            override val className = "$PACKAGE_NAME.home.views.HomeActivity"
        }

    }

}