package io.freshdroid.mymonzo.core.viewmodel

import android.content.Intent
import io.freshdroid.mymonzo.core.ui.ActivityResult
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

open class ActivityViewModel : AutoDisposeViewModel() {

    private val _activityResult = PublishSubject.create<ActivityResult>()
    private val _intent = PublishSubject.create<Intent>()

    fun activityResult(activityResult: ActivityResult) {
        this._activityResult.onNext(activityResult)
    }

    fun intent(intent: Intent?) {
        intent?.let {
            this._intent.onNext(intent)
        }
    }

    protected fun activityResult(): Observable<ActivityResult> = _activityResult

    protected fun intent(): Observable<Intent> = _intent

}