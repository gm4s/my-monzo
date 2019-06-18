package io.freshdroid.core.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import io.freshdroid.core.ui.ActivityResult
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

open class FragmentViewModel : ViewModel() {

    private val activityResult = PublishSubject.create<ActivityResult>()
    private val arguments = PublishSubject.create<Bundle>()

    fun activityResult(activityResult: ActivityResult) {
        this.activityResult.onNext(activityResult)
    }

    fun arguments(arguments: Bundle?) {
        arguments?.let {
            this.arguments.onNext(arguments)
        }
    }

    protected fun activityResult(): Observable<ActivityResult> = activityResult

    protected fun arguments(): Observable<Bundle> = arguments

}