package com.dyrosoft.kvbparser.rx;

import com.dyrosoft.kvbparser.utils.StringUtils;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Response;
import rx.Single;
import rx.functions.Func1;

public class ResponseFunc implements Func1<Response<ResponseBody>, Single<String>> {

    @Override
    public Single<String> call(final Response<ResponseBody> response) {
        if (response.isSuccess()) {
            try {
                return Single.just(StringUtils.convertToUtf8(response.body().string()));
            } catch (IOException e) {
                return Single.error(e);
            }
        } else {
            return Single.error(new Throwable(response.message()));
        }
    }
}
