package io.freshdroid.mymonzo.core.rx

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus : RxBusType {

    private val mPublisher = PublishSubject.create<Any>()

    override fun publish(event: Any) {
        mPublisher.onNext(event)
    }

    override fun <T> toObservable(eventType: Class<T>): Observable<T> = mPublisher.ofType(eventType)

}