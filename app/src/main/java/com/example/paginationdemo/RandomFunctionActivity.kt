package com.example.paginationdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_random_function.*

class RandomFunctionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random_function)


        btnAdd.setOnClickListener {

            if (number.text?.trim()!!.isEmpty()) {

                Snackbar.make(main_constrain, "enter number", Snackbar.LENGTH_SHORT).show()
            } else {
                val num: Int = number.text.toString().toInt()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("button", num)
                startActivity(intent)
            }


        }

    }
}