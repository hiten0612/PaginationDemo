package com.example.paginationdemo.network

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paginationdemo.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Log.v("#########", "hello")

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerViewAdapter


        val viewModel = ViewModelProvider.AndroidViewModelFactory(Application())
            .create(MainActivityViewModel::class.java)


//        val viewModel = ViewModelProvider(this,CharacterListDataSourceFactory(this)).

        viewModel.getRecyclerListObserver()?.observe(this, Observer<PagedList<CharacterData>> {

            if (it != null) {

                recyclerViewAdapter.submitList(it)
                Log.v("@@@@@@@@@@", "response suceess")

            } else {
                Snackbar.make(constraint, "Failed to fetch data", Snackbar.LENGTH_SHORT).show()
                Log.v("@@@@@@@@@@", "response fail")
            }

        })
    }


}


