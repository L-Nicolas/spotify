package fr.upsilon.spotify.Model.services

import okhttp3.Interceptor
import okhttp3.Response

object RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        println("Outgoing request to ")
        return chain.proceed(request)
    }
}
