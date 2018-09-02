package com.star_zero.pagingeditsample.data.repository

import com.star_zero.pagingeditsample.data.model.Listing
import com.star_zero.pagingeditsample.data.model.RedditPost

interface RedditRepository {

    fun redditPaging(): Listing<RedditPost>

    fun delete(redditPost: RedditPost)

    fun clearDb()
}
