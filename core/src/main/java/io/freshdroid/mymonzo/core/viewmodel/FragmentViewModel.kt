package io.freshdroid.mymonzo.core.viewmodel

import android.os.Bundle
import io.freshdroid.mymonzo.core.ui.ActivityResult
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

open class FragmentViewModel : AutoDisposeViewModel() {

    private val _activityResult = PublishSubject.create<ActivityResult>()
    private val _arguments = PublishSubject.create<Bundle>()

    fun activityResult(activityResult: ActivityResult) {
        this._activityResult.onNext(activityResult)
    }

    fun arguments(arguments: Bundle?) {
        arguments?.let {
            this._arguments.onNext(arguments)
        }
    }

    protected fun activityResult(): Observable<ActivityResult> = _activityResult

    protected fun arguments(): Observable<Bundle> = _arguments

}