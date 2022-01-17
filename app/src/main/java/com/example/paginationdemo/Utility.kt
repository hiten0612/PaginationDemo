package com.example.paginationdemo

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast

object Utility {


    private var progressBar: ProgressBar? = null


    fun Context.isInternetAvailable(): Boolean {

        try {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return if (netInfo != null && netInfo.isConnected) {
                true
            } else {
                showErrorToast("Internet not available. Please try again!!")

                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun Context.showErrorToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        //  Snackbar.make(this, message, Snackbar.LE).show()
    }

    fun Context.showProgressBar() {
        try {
            val layout =
                (this as? Activity)?.findViewById<View>(android.R.id.content)?.rootView as? ViewGroup

            progressBar = ProgressBar(this, null, R.attr.progressBarStyle)

            progressBar?.let {
                it.isIndeterminate = true
                val params = RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
                )

                val rl = RelativeLayout(this)
                rl.gravity = Gravity.CENTER
                rl.addView(it)
                layout?.addView(rl, params)
                it.visibility = View.VISIBLE
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