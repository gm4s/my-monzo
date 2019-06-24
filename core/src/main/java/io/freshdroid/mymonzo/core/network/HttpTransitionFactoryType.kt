package io.freshdroid.mymonzo.core.network

import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Response

interface HttpTransitionFactoryType {

    fun create(
        headers: Map<String, String>? = null,
        transition: HttpTransition,
        parameters: String? = null,
        multipartBody: MultipartBody.Part? = null
    ): Observable<Response<String>>

}