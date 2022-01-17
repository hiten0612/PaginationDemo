package com.example.paginationdemo.network

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paginationdemo.R

class RecyclerViewAdapter :
    PagingDataAdapter<CharacterData, RecyclerViewAdapter.MyViewHolder>(DiffUtilCallBack()) {


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.bind(getItem(position)!!)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return MyViewHolder(view)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        val imageThumb: ImageView = view.findViewById(R.id.imageThumb)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvSpecies: TextView = view.findViewById(R.id.tvSpecies)

        fun bind(data: CharacterData) {

            tvName.text = data.name
            tvSpecies.text = data.species

            Glide.with(imageThumb)
                .load(data.image)
                .circleCrop()
                .into(imageThumb)
        }
    }
}