package com.dyrosoft.kvbparser.api;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

public final class ApiProvider {

    private static class HeaderInterceptor implements Interceptor {

        private static final String HEADER_USER_AGENT =
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.137 Safari/537.36";

        @Override
        public Response intercept(final Chain chain) throws IOException {
            final Request request = chain.request();

            final Request.Builder builder = request.newBuilder();
            builder.addHeader("user-agent", HEADER_USER_AGENT);
            final Request modifiedRequest = builder.build();

            return chain.proceed(modifiedRequest);
        }
    }

    private static final String BASE_URL = "http://www.kvb-koeln.de/";

    private ApiProvider() {
    }

    public static KvbApi getKvbApi() {
        return buildRetrofit().create(KvbApi.class);
    }

    private static Retrofit buildRetrofit() {
        final OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new HeaderInterceptor());
        final String endpoint = BASE_URL;

        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
