package io.freshdroid.mymonzo.core.network

import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET
    fun get(@HeaderMap headerMap: Map<String, String>, @Url url: String): Observable<Response<String>>

    @POST
    fun post(@HeaderMap headerMap: Map<String, String>, @Url url: String): Observable<Response<String>>

    @POST
    fun post(@HeaderMap headerMap: Map<String, String>, @Url url: String, @Body body: String): Observable<Response<String>>

    @Multipart
    @POST
    fun post(@Url url: String, @Part body: MultipartBody.Part): Observable<Response<String>>

    @PUT
    fun put(@HeaderMap headerMap: Map<String, String>, @Url url: String): Observable<Response<String>>

    @PUT
    fun put(@HeaderMap headerMap: Map<String, String>, @Url url: String, @Body body: String): Observable<Response<String>>

    @PATCH
    fun patch(@HeaderMap headerMap: Map<String, String>, @Url url: String): Observable<Response<String>>

    @PATCH
    fun patch(@HeaderMap headerMap: Map<String, String>, @Url url: String, @Body body: String): Observable<Response<String>>

    @DELETE
    fun delete(@HeaderMap headerMap: Map<String, String>, @Url url: String): Observable<Response<String>>

    @HTTP(method = "DELETE", hasBody = true)
    fun delete(@HeaderMap headerMap: Map<String, String>, @Url url: String, @Body body: String): Observable<Response<String>>

}