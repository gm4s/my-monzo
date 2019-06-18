package io.freshdroid.mymonzo.navigation

import android.net.Uri

class IntentNotFoundException(uri: Uri) : RuntimeException("Unable to create the associated intent to the Uri: $uri")