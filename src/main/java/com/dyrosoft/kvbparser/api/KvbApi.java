package com.dyrosoft.kvbparser.api;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Single;

public interface KvbApi {

    @GET("qr/{stationId}/")
    Single<Response<ResponseBody>> queryDepartures(@Path("stationId") final String stationId);

    @GET("german/hst/showline/{stationId}/{lineId}/")
    Single<Response<ResponseBody>> queryLineDetails(@Path("stationId") final String stationId,
                                                    @Path("lineId") final String lineId);

    @GET("german/hst/overview/{stationId}/")
    Single<Response<ResponseBody>> queryStationDetail(@Path("stationId") final String stationId);

    @GET("german/hst/overview/")
    Single<Response<ResponseBody>> queryStations();
}
