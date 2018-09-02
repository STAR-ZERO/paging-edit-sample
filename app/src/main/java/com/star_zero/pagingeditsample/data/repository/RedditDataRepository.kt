package com.star_zero.pagingeditsample.data.repository

import androidx.paging.LivePagedListBuilder
import com.star_zero.pagingeditsample.data.api.RedditService
import com.star_zero.pagingeditsample.data.db.RedditDb
import com.star_zero.pagingeditsample.data.model.Listing
import com.star_zero.pagingeditsample.data.model.RedditPost

class RedditDataRepository constructor(
    private val redditService: RedditService,
    private val redditDb: RedditDb
) : RedditRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override fun redditPaging(): Listing<RedditPost> {
        val boundaryCallback = RedditBoundaryCallback(
            redditService,
            redditDb,
            PAGE_SIZE
        )

        val dataSourceFactory = redditDb.redditDao().loadAll()
        val builder = LivePagedListBuilder(dataSourceFactory, PAGE_SIZE).setBoundaryCallback(boundaryCallback)

        return Listing(
            pagedList = builder.build(),
            clear = { boundaryCallback.clear() }
        )
    }

    override fun delete(redditPost: RedditPost) {
        redditDb.redditDao().delete(redditPost)
    }

    override fun clearDb() {
        redditDb.redditDao().deleteAll()
    }
}
