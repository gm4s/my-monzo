package io.freshdroid.mymonzo.core.ui

import android.content.Intent
import android.os.Parcelable
import io.freshdroid.mymonzo.core.predicates.ActivityExtraPredicate
import io.reactivex.Observable
import timber.log.Timber

object IntentExtra {

    @JvmStatic
    @Throws(IntentExtraNotFoundException::class)
    fun <T : Parcelable> getParcelableFromKey(
        intent: Observable<Intent>,
        key: String,
        isMandatory: Boolean = false
    ): Observable<T> {
        return intent
            .filter { checkActivityExtra(it, key, isMandatory) }
            .map { it.getParcelableExtra(key) as T }
            .doOnNext { Timber.d(key, it.toString()) }
    }

    @JvmStatic
    @Throws(IntentExtraNotFoundException::class)
    fun getStringFromKey(intent: Observable<Intent>, key: String, isMandatory: Boolean = false): Observable<String> {
        return intent
            .filter { checkActivityExtra(it, key, isMandatory) }
            .map { it.getStringExtra(key) }
            .doOnNext { Timber.d(key, it) }
    }

    @JvmStatic
    @Throws(IntentExtraNotFoundException::class)
    fun getIntFromKey(intent: Observable<Intent>, key: String, isMandatory: Boolean = false): Observable<Int> {
        return intent
            .filter { checkActivityExtra(it, key, isMandatory) }
            .map { it.getIntExtra(key, -1) }
            .doOnNext { Timber.d(key, it.toString()) }
    }

    @JvmStatic
    @Throws(IntentExtraNotFoundException::class)
    fun getBooleanFromKey(intent: Observable<Intent>, key: String, isMandatory: Boolean = false): Observable<Boolean> {
        return intent
            .filter { checkActivityExtra(it, key, isMandatory) }
            .map { it.getBooleanExtra(key, false) }
            .doOnNext { Timber.d(key, it.toString()) }
    }

    @Throws(IntentExtraNotFoundException::class)
    private fun checkActivityExtra(intent: Intent, key: String, isMandatory: Boolean): Boolean {
        return if (isMandatory) {
            if (ActivityExtraPredicate.hasExtra(intent, key)) {
                true
            } else {
                throw IntentExtraNotFoundException(key)
            }
        } else {
            ActivityExtraPredicate.hasExtra(intent, key)
        }
    }

}