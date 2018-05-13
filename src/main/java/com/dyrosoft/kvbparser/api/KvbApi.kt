package com.dyrosoft.kvbparser.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Single

internal interface KvbApi {

    @GET("qr/{stationId}/")
    fun queryDepartures(@Path("stationId") stationId: String): Single<Response<ResponseBody>>

    @GET("haltestellen/showline/{stationId}/{lineId}/")
    fun queryLineDetails(@Path("stationId") stationId: String,
                         @Path("lineId") lineId: String): Single<Response<ResponseBody>>

    @GET("haltestellen/overview/{stationId}/")
    fun queryStationDetail(@Path("stationId") stationId: String): Single<Response<ResponseBody>>

    @GET("haltestellen/overview/")
    fun queryStations(): Single<Response<ResponseBody>>
}
