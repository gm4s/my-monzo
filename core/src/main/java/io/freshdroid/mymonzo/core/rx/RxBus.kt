package io.freshdroid.mymonzo.core.rx

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus : RxBusType {

    private val _publisher = PublishSubject.create<Any>()

    override fun publish(event: Any) {
        _publisher.onNext(event)
    }

    override fun <T> toObservable(eventType: Class<T>): Observable<T> = _publisher.ofType(eventType)

}