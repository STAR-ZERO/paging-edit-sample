package com.star_zero.pagingeditsample.di

import android.content.Context
import com.star_zero.pagingeditsample.App
import com.star_zero.pagingeditsample.data.api.RedditService
import com.star_zero.pagingeditsample.data.db.RedditDb
import com.star_zero.pagingeditsample.data.repository.RedditDataRepository
import com.star_zero.pagingeditsample.data.repository.RedditRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: App): Context {
        return app
    }

    @Singleton
    @Provides
    fun provideRedditRepository(redditService: RedditService, redditDb: RedditDb): RedditRepository {
        return RedditDataRepository(redditService, redditDb)
    }
}
