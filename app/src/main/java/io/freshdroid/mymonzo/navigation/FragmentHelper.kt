@file:JvmName("FragmentHelper")

package io.freshdroid.mymonzo.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

private const val PACKAGE_NAME = "io.freshdroid.mymonzo"

fun fragmentTo(
    context: Context,
    addressableFragment: AddressableFragment,
    arguments: Bundle? = null
): Fragment {
    return Fragment.instantiate(context, addressableFragment.className, arguments)
}

interface AddressableFragment {
    val className: String
}

object Fragments {

    object Home {

        object Feed : AddressableFragment {
            override val className = "$PACKAGE_NAME.feed.views.FeedFragment"
        }

    }

}