package com.star_zero.pagingeditsample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.star_zero.pagingeditsample.R
import com.star_zero.pagingeditsample.data.model.RedditPost
import com.star_zero.pagingeditsample.databinding.ItemPostBinding

class Adapter(private val eventHandler: EventHandler) : PagedListAdapter<RedditPost, Adapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RedditPost>() {
            override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemPostBinding>(inflater, R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), eventHandler)
    }

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(redditPost: RedditPost?, eventHandler: EventHandler) {
            binding.redditPost = redditPost
            binding.handler = eventHandler
            binding.executePendingBindings()
        }
    }

    interface EventHandler {
        // 削除ボタンクリック
        fun clickDelete(redditPost: RedditPost)
    }
}
