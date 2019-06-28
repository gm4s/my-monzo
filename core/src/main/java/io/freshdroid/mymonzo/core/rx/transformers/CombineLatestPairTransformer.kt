package io.freshdroid.mymonzo.core.rx.transformers

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction

class CombineLatestPairTransformer<S, T>(
        private val _second: Observable<T>
) : ObservableTransformer<S, Pair<S, T>> {

    override fun apply(upstream: Observable<S>): ObservableSource<Pair<S, T>> {
        return Observable.combineLatest(upstream, _second, BiFunction { f, s -> Pair(f, s) })
    }

}