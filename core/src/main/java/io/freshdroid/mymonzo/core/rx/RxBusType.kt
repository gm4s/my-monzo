package io.freshdroid.mymonzo.core.rx

import io.reactivex.Observable

interface RxBusType {

    fun publish(event: Any)

    fun <T> toObservable(eventType: Class<T>): Observable<T>

}