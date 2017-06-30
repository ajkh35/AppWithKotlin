package com.example.ajay3.appwithkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        list.adapter = MyAdapter(this)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = RecyclerAdapter(this)
    }

    fun MainActivity.toast(charSequence: CharSequence,duration: Int = Toast.LENGTH_SHORT){
        Toast.makeText(this,charSequence,duration).show()
    }
}