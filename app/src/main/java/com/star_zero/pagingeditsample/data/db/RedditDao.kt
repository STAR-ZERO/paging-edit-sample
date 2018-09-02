package com.star_zero.pagingeditsample.data.db

import androidx.paging.DataSource
import androidx.room.*
import com.star_zero.pagingeditsample.data.model.RedditPost

@Dao
interface RedditDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts : List<RedditPost>)

    @Query("SELECT * FROM posts ORDER BY sortKey ASC")
    fun loadAll() : DataSource.Factory<Int, RedditPost>

    @Delete
    fun delete(redditPost: RedditPost)

    @Query("DELETE FROM posts")
    fun deleteAll()

    @Query("SELECT MAX(sortKey) + 1 FROM posts")
    fun getNextSortKey() : Int
}
