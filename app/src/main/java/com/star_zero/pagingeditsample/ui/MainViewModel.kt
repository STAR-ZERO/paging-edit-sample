package com.star_zero.pagingeditsample.ui

import androidx.lifecycle.ViewModel
import com.star_zero.pagingeditsample.data.model.RedditPost
import com.star_zero.pagingeditsample.data.repository.RedditRepository
import timber.log.Timber
import java.util.concurrent.Executors
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val redditRepository: RedditRepository
) : ViewModel() {

    private val listing = redditRepository.redditPaging()
    val pagedList = listing.pagedList

    override fun onCleared() {
        listing.clear()
    }

    fun delete(redditPost: RedditPost) {
        Timber.d("delete")

        Executors.newSingleThreadExecutor().submit {
            // このサンプルではAPIは何もしてないけど、実際は削除APIを呼んで成功したらDB削除するみたいな感じ

            // DBデータを削除すると、RecyclerViewの表示も自動で反映される
            redditRepository.delete(redditPost)
        }
    }

    fun clearDb() {
        Timber.d("clearDb")

        // DBをクリアすると自動で、再取得する
        Executors.newSingleThreadExecutor().submit {
            redditRepository.clearDb()
        }
    }
}
