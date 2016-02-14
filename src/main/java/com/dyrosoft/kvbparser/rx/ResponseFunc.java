package com.dyrosoft.kvbparser.rx;

import com.google.common.base.Charsets;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;
import rx.Single;
import rx.functions.Func1;

public class ResponseFunc implements Func1<Response<ResponseBody>, Single<String>> {

    @Override
    public Single<String> call(final Response<ResponseBody> response) {
        if (response.isSuccess()) {
            try {
                return Single.just(new String(response.body().bytes(), Charsets.ISO_8859_1));
            } catch (IOException e) {
                return Single.error(e);
            }
        } else {
            return Single.error(new Throwable(response.message()));
        }
    }
}
