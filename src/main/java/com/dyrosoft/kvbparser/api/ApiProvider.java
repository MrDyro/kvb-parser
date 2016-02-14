package com.dyrosoft.kvbparser.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

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
        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .build();

        final String endpoint = BASE_URL;
        return new Retrofit.Builder()
                .baseUrl(endpoint)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
