package com.example.core_network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request().url.queryParameter(API_KEY_QUERY)?.let {
            chain.proceed(chain.request()) // If API KEY is already specified, just proceed
        } ?: chain.proceed( // Otherwise, create a new request with API KEY added
            request = chain.request().newBuilder()
                .url(
                    chain.request().url.newBuilder()
                        // Normally, the API KEY shouldn't be pushed to the repo, but it's okay for a test project
                        .addQueryParameter(API_KEY_QUERY, "a6431c04cebf9404c92eb2b0e41f095a")
                        .build()
                )
                .build()
        )
    }

    companion object {
        private const val API_KEY_QUERY = "appid"
    }
}
