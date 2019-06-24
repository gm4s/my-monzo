package io.freshdroid.mymonzo.home

import io.freshdroid.mymonzo.MyMonzoApplication

internal class MyMonzoApplicationTest : MyMonzoApplication() {

    override fun isInUnitTests(): Boolean = true

    override fun onCreate() {
        super.onCreate()
    }

}