package io.freshdroid.mymonzo.core.ui

class IntentExtraNotFoundException(key: String) :
    RuntimeException("Unable to find the extras associated to the intent with the key: $key")