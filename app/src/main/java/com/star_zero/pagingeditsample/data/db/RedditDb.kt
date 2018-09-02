package com.star_zero.pagingeditsample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.star_zero.pagingeditsample.data.model.RedditPost

@Database(
    entities = [RedditPost::class],
    version = 1,
    exportSchema = false
)
abstract class RedditDb: RoomDatabase() {
    abstract fun redditDao() : RedditDao
}
