package com.star_zero.pagingeditsample.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("/r/androiddev/hot.json")
    fun getTop(@Query("limit") limit: Int): Single<ListingResponse>

    @GET("/r/androiddev/hot.json")
    fun getTopAfter(@Query("after") after: String, @Query("limit") limit: Int): Single<ListingResponse>
}
