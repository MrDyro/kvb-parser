package com.dyrosoft.kvbparser.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import java.io.IOException

object ApiProvider {

    private class HeaderInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response =
                chain.proceed(chain.request()
                                      .newBuilder()
                                      .addHeader("user-agent", HEADER_USER_AGENT)
                                      .build())

        companion object {
            private const val HEADER_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36"
        }
    }

    private const val BASE_URL = "http://www.kvb-koeln.de/"

    val kvbApi: KvbApi
        get() = buildRetrofit().create(KvbApi::class.java)

    private fun buildRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
                .build()

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
}
