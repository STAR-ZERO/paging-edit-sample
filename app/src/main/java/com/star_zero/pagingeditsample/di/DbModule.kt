package com.star_zero.pagingeditsample.di

import android.content.Context
import androidx.room.Room
import com.star_zero.pagingeditsample.data.db.RedditDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    companion object {
        private const val DB_NAME = "reddit.db"
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context): RedditDb {
        return Room.databaseBuilder(context, RedditDb::class.java, DB_NAME).build()
    }
}
