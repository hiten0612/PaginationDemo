package com.example.paginationdemo.network

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.*
import kotlinx.coroutines.Dispatchers
import java.util.concurrent.Executors

class MainActivityViewModel(val context: Context) : ViewModel() {

    private var characterList: LiveData<PagedList<CharacterData>>? = null

    init {
        initPaging()
    }

    private fun initPaging() {
        val factory = CharacterListDataSourceFactory(context)

        val config = PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(30).build()

        val executors = Executors.newFixedThreadPool(5)

        characterList =
            Pager(
                PagingConfig(
                    config.pageSize,
                    config.prefetchDistance,
                    config.enablePlaceholders,
                    config.initialLoadSizeHint,
                    config.maxSize
                ),
                this.initialLoadKey,
                factory.asPagingSourceFactory(Dispatchers.IO)
            ).liveData.setFetchExecutor(executors)
                .build()
    }

    fun getRecyclerListObserver(): LiveData<PagedList<CharacterData>>? {

        return characterList
    }

}