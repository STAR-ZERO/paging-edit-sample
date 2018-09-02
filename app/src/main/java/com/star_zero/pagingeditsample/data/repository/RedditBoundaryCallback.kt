package com.star_zero.pagingeditsample.data.repository

import androidx.paging.PagedList
import com.star_zero.pagingeditsample.data.api.ListingResponse
import com.star_zero.pagingeditsample.data.api.RedditService
import com.star_zero.pagingeditsample.data.db.RedditDb
import com.star_zero.pagingeditsample.data.model.RedditPost
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RedditBoundaryCallback constructor(
    private val redditService: RedditService,
    private val redditDb: RedditDb,
    private val pageSize: Int
) : PagedList.BoundaryCallback<RedditPost>() {

    private val disposable = CompositeDisposable()

    override fun onZeroItemsLoaded() {
        Timber.d("onZeroItemsLoaded")
        // DBから初回取得時にデータが無いとき

        redditService.getTop(pageSize)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    // DBへ保存
                    insertDb(it)
                },
                {
                    Timber.w(it)
                }
            ).also { disposable.add(it) }
    }

    override fun onItemAtEndLoaded(itemAtEnd: RedditPost) {
        Timber.d("onItemAtEndLoaded")
        // DBに2ページ以降のデータが無いとき

        // 引数に前回取得したデータの最後のものが渡される

        redditService.getTopAfter(itemAtEnd.name, pageSize)
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    // DBへ保存
                    insertDb(it)
                },
                {
                    Timber.w(it)
                }
            ).also { disposable.add(it) }
    }

    private fun insertDb(response: ListingResponse) {
        redditDb.runInTransaction {
            val dao = redditDb.redditDao()
            val startSortKey = dao.getNextSortKey()

            // APIの結果からEntityに変換して保存
            val posts = response.data.children.mapIndexed { index, child ->
                RedditPost(
                    child.data.name,
                    child.data.title,
                    startSortKey + index  // ソート用のキー（Reddit APIにはソートできそうなキーがないので）
                )
            }
            dao.insert(posts)
        }
    }

    fun clear() {
        Timber.d("Clear")
        disposable.clear()
    }
}
