package io.freshdroid.mymonzo.splashscreen.network

import com.squareup.moshi.Moshi
import io.freshdroid.mymonzo.core.network.ApiCore
import io.freshdroid.mymonzo.core.network.HttpTransitionFactoryType
import javax.inject.Inject

class ApiSplashScreen @Inject constructor(
    private val httpTransitionFactory: HttpTransitionFactoryType,
    private val moshi: Moshi
) : ApiCore(moshi), ApiSplashScreenType