package com.star_zero.pagingeditsample.data.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListingResponse(
    val data: ListingDataResponse
)

@JsonClass(generateAdapter = true)
data class ListingDataResponse(
    val children: List<RedditChildrenResponse>,
    val after: String?,
    val before: String?
)

@JsonClass(generateAdapter = true)
data class RedditChildrenResponse(
    val data: RedditPostResponse
)

@JsonClass(generateAdapter = true)
data class RedditPostResponse(
    val name: String,
    val title: String
)
