package io.freshdroid.core.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import io.freshdroid.core.ui.ActivityResult
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

open class ActivityViewModel : ViewModel() {

    private val activityResult = PublishSubject.create<ActivityResult>()
    private val intent = PublishSubject.create<Intent>()

    fun activityResult(activityResult: ActivityResult) {
        this.activityResult.onNext(activityResult)
    }

    fun intent(intent: Intent?) {
        intent?.let {
            this.intent.onNext(intent)
        }
    }

    protected fun activityResult(): Observable<ActivityResult> = activityResult

    protected fun intent(): Observable<Intent> = intent

}