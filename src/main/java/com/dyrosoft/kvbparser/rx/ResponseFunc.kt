package com.dyrosoft.kvbparser.rx

import okhttp3.ResponseBody
import retrofit2.Response
import rx.Single
import rx.functions.Func1
import java.io.IOException

internal class ResponseFunc : Func1<Response<ResponseBody>, Single<String>> {

    override fun call(response: Response<ResponseBody>): Single<String> {
        if (response.isSuccessful && response.body() != null) {
            try {
                return Single.just(String(response.body()!!.bytes(), Charsets.ISO_8859_1))
            } catch (e: IOException) {
                return Single.error<String>(e)
            }

        } else {
            return Single.error<String>(Throwable(response.message()))
        }
    }
}
