package io.freshdroid.mymonzo.feed

import io.freshdroid.mymonzo.MyMonzoApplication

internal class MyMonzoApplicationTest : MyMonzoApplication() {

    override fun isInUnitTests(): Boolean = true

    override fun onCreate() {
        super.onCreate()
    }

}