package com.example.paginationdemo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*
import kotlin.collections.ArrayList

class ResultActivity : AppCompatActivity(), RandomAdapter.Listener {

    private val arrayList = ArrayList<RandomModel>()

    // private val arrayList = ArrayList<String>()

    private var adapter: RandomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)


        val data = intent.getIntExtra("button", 1)

        for (i in 1..data) {

//            arrayList.add(i.toString())

            val random = Random()
            val color: Int =
                Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(256))

            arrayList.add(RandomModel(i.toString(), color))


        }

        randomRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RandomAdapter(this, arrayList, this)
        randomRecyclerView.adapter = adapter

    }


    override fun onItemClick(random: Any, position: Int) {


        val color = arrayList[position].color

        arrayList[position].color = resources.getColor(R.color.red)

        for (i in 0 until arrayList.size) {

            if (i > position) {
                arrayList[i].color = color
            }
        }

        adapter?.notifyDataSetChanged()


    }


}