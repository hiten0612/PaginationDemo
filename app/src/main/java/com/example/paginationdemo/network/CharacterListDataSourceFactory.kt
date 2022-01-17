package com.example.paginationdemo.network

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


class CharacterListDataSourceFactory(val context: Context) :
    DataSource.Factory<Int, CharacterData>() {

    private var mutableLiveData: MutableLiveData<CharacterListDataSource>? = null


    init {
        mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Int, CharacterData> {

        val listDataSource = CharacterListDataSource(context)
        mutableLiveData?.postValue(listDataSource)

        return listDataSource


    }
}