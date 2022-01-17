package com.example.paginationdemo.network

import androidx.recyclerview.widget.DiffUtil

class DiffUtilCallBack : DiffUtil.ItemCallback<CharacterData>() {
    override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {

        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {


        return oldItem.name == newItem.name && oldItem.species == newItem.species
    }
}