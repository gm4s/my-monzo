package io.freshdroid.mymonzo.navigation

object ApplicationMap {

    object Home {
        const val FEED = "mymonzo://home/feed"
        const val SUMMARY = "mymonzo://home/summary"
        const val ACCOUNT = "mymonzo://home/account"
        const val HELPS = "mymonzo://home/helps"
    }

    object Transaction {
        const val DETAILS = "mymonzo://transaction/details"
    }

    object Lock {
        const val FINGER_PRINT = "mymonzo://lock/finger-print"
        const val PIN = "mymonzo://lock/pin"
    }

}