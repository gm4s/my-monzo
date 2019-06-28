package io.freshdroid.mymonzo.core.rx.transformers

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction

class TakeWhenTransformer<S, T>(
        private val _source: Observable<T>
) : ObservableTransformer<S, S> {

    override fun apply(upstream: Observable<S>): ObservableSource<S> {
        return _source.withLatestFrom(upstream, BiFunction { _, x -> x })
    }

}