package io.freshdroid.mymonzo.core.network

import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Response
import javax.inject.Inject

const val KEY_HEADER_CONTENT_TYPE = "Content-Type"
const val DEFAULT_CONTENT_TYPE = "application/json"

class HttpTransitionFactory @Inject constructor(
    private val apiService: ApiService
) : HttpTransitionFactoryType {

    override fun create(
        headers: Map<String, String>?,
        transition: HttpTransition,
        parameters: String?,
        multipartBody: MultipartBody.Part?
    ): Observable<Response<String>> {
        return when (transition.verb) {
            HttpVerb.GET -> this.get(headers, transition, parameters)
            HttpVerb.POST -> this.post(headers, transition, parameters, multipartBody)
            HttpVerb.PUT -> this.put(headers, transition, parameters)
            HttpVerb.PATCH -> this.patch(headers, transition, parameters)
            HttpVerb.DELETE -> this.delete(headers, transition, parameters)
        }
    }

    private fun get(
        headers: Map<String, String>?,
        httpTransition: HttpTransition,
        queryParams: String?
    ): Observable<Response<String>> {
        val contentType = httpTransition.accept ?: DEFAULT_CONTENT_TYPE
        val headerMap = hashMapOf(Pair(KEY_HEADER_CONTENT_TYPE, contentType))
        headers?.let { headerMap.putAll(it) }

        val url = queryParams?.let { "${httpTransition.url}?$it" } ?: httpTransition.url

        return apiService.get(headerMap, url)
    }

    private fun post(
        headers: Map<String, String>?,
        httpTransition: HttpTransition,
        body: String?,
        multipartBody: MultipartBody.Part?
    ): Observable<Response<String>> {
        val contentType = httpTransition.accept ?: DEFAULT_CONTENT_TYPE
        val headerMap = hashMapOf(Pair(KEY_HEADER_CONTENT_TYPE, contentType))
        headers?.let { headerMap.putAll(it) }

        return if (multipartBody == null) {
            if (body == null) {
                apiService.post(headerMap, httpTransition.url)
            } else {
                apiService.post(headerMap, httpTransition.url, body)
            }
        } else {
            apiService.post(httpTransition.url, multipartBody)
        }
    }

    private fun put(
        headers: Map<String, String>?,
        httpTransition: HttpTransition,
        body: String?
    ): Observable<Response<String>> {
        val contentType = httpTransition.accept ?: DEFAULT_CONTENT_TYPE
        val headerMap = hashMapOf(Pair(KEY_HEADER_CONTENT_TYPE, contentType))
        headers?.let { headerMap.putAll(it) }

        return if (body == null) {
            apiService.put(headerMap, httpTransition.url)
        } else {
            apiService.put(headerMap, httpTransition.url, body)
        }
    }

    private fun patch(
        headers: Map<String, String>?,
        httpTransition: HttpTransition,
        body: String?
    ): Observable<Response<String>> {
        val contentType = httpTransition.accept ?: DEFAULT_CONTENT_TYPE
        val headerMap = hashMapOf(Pair(KEY_HEADER_CONTENT_TYPE, contentType))
        headers?.let { headerMap.putAll(it) }

        return if (body == null) {
            apiService.patch(headerMap, httpTransition.url)
        } else {
            apiService.patch(headerMap, httpTransition.url, body)
        }
    }

    private fun delete(
        headers: Map<String, String>?,
        httpTransition: HttpTransition,
        body: String?
    ): Observable<Response<String>> {
        val contentType = httpTransition.accept ?: DEFAULT_CONTENT_TYPE
        val headerMap = hashMapOf(Pair(KEY_HEADER_CONTENT_TYPE, contentType))
        headers?.let { headerMap.putAll(it) }

        return if (body == null) {
            apiService.delete(headerMap, httpTransition.url)
        } else {
            apiService.delete(headerMap, httpTransition.url, body)
        }
    }

}