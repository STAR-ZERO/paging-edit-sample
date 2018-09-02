package com.star_zero.pagingeditsample.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class RedditPost(
    @PrimaryKey
    val name: String,
    var title: String,
    val sortKey: Int
)
