package io.freshdroid.mymonzo.core.rx.operators

import com.squareup.moshi.Moshi
import io.freshdroid.mymonzo.core.network.ErrorEnvelope
import io.freshdroid.mymonzo.core.network.exceptions.ApiException
import io.freshdroid.mymonzo.core.network.exceptions.ResponseException
import io.reactivex.ObservableOperator
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response

class ApiErrorOperator(
    private val moshi: Moshi
) : ObservableOperator<String, Response<String>> {

    override fun apply(observer: Observer<in String>): Observer<in Response<String>> {
        return object : Observer<Response<String>> {

            override fun onComplete() {
                observer.onComplete()
            }

            override fun onSubscribe(d: Disposable) {
                observer.onSubscribe(d)
            }

            override fun onNext(response: Response<String>) {
                if (!response.isSuccessful) {
                    try {
                        val adapter = moshi.adapter(ErrorEnvelope::class.java)
                        val envelope = adapter.fromJson(response.errorBody()?.string() ?: "{}")

                        if (envelope != null) {
                            val envelopeWithCodeError = envelope.copy(code = response.code())
                            observer.onError(ApiException(envelopeWithCodeError, response))
                        } else {
                            observer.onError(ResponseException(response))
                        }
                    } catch (e: Exception) {
                        observer.onError(ResponseException(response))
                    }
                } else {
                    val body = if (response.body().isNullOrEmpty()) "{}" else requireNotNull(response.body())
                    observer.onNext(body)
                    observer.onComplete()
                }
            }

            override fun onError(e: Throwable) {
                observer.onError(e)
            }
        }
    }
}