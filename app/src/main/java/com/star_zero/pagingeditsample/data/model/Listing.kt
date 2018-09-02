package com.star_zero.pagingeditsample.data.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val clear: () -> Unit
)
