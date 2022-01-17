package com.example.paginationdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class RandomAdapter(
    val context: Context,
    var rList: ArrayList<RandomModel>,
    val listener: Listener
) :
    RecyclerView.Adapter<RandomAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.random_list, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.randomBtn.setBackgroundColor(rList[position].color!!)


        holder.randomBtn.text = rList[position].str


        holder.randomBtn.setOnClickListener {
            listener.onItemClick(rList[position], position)
            holder.randomBtn.resources.getColor(R.color.red)
        }
    }

    override fun getItemCount(): Int {

        return rList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val randomBtn: Button = itemView.findViewById(R.id.randomBtn)

    }

    interface Listener {

        fun onItemClick(random: Any, position: Int)

    }
}