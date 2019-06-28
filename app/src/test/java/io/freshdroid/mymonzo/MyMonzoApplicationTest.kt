package io.freshdroid.mymonzo

internal class MyMonzoApplicationTest : MyMonzoApplication() {

    override fun isInUnitTests(): Boolean = true

    override fun onCreate() {
        super.onCreate()
    }

}