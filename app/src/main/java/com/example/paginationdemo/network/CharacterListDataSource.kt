package com.example.paginationdemo.network

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.paging.PagingSource
import com.example.paginationdemo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListDataSource(val context: Context) : PagingSource<Int, CharacterData>() {

    private var progressBar: ProgressBar? = null

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterData>) {


        showProgressBar()
        val retroInstance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(params.key)

        call.enqueue(object : Callback<RickAndMortyList> {
            override fun onResponse(
                call: Call<RickAndMortyList>,
                response: Response<RickAndMortyList>
            ) {

                hideProgressBar()
                if (response.isSuccessful) {
                    callback.onResult(response.body()?.results!!, params.key + 1)
                }

            }

            override fun onFailure(call: Call<RickAndMortyList>, t: Throwable) {

                hideProgressBar()

            }

        })


    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CharacterData>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CharacterData>
    ) {

        showProgressBar()

        val retroInstance = RetroInstance.getRetrofitInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(1)

        call.enqueue(object : Callback<RickAndMortyList> {
            override fun onResponse(
                call: Call<RickAndMortyList>,
                response: Response<RickAndMortyList>
            ) {

                hideProgressBar()
                if (response.isSuccessful) {
                    Log.v("get response-->", "${response.body()}")
                    callback.onResult(response.body()?.results!!, null, 2)

                }


            }

            override fun onFailure(call: Call<RickAndMortyList>, t: Throwable) {

            }

        })


    }

    private fun showProgressBar() {
        try {
            val layout =
                (this as? Activity)?.findViewById<View>(android.R.id.content)?.rootView as ViewGroup

            progressBar = ProgressBar(this.context, null, R.attr.progressBarStyle)

            progressBar.let {
                it?.isIndeterminate = true
                val params = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
                )

                val r1 = RelativeLayout(context)
                r1.gravity = Gravity.BOTTOM
                r1.addView(it)
                layout.addView(it, params)
                it?.visibility = View.VISIBLE

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideProgressBar() {
        try {
            progressBar?.let {
                it.visibility = View.GONE

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}