package com.example.ajay3.appwithkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_view_movie.*
import org.json.JSONObject

class ViewMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_movie)

        val bundle = intent.extras
        val jsonText = bundle.getString("MOVIE")
        val movie = JSONObject(jsonText)
        fillViews(movie)
    }

    fun ViewMovie.fillViews(movie: JSONObject){
        view_movie_recycler_item_text.text = movie.getString("title")
        view_movie_recycler_desc.text = movie.getString("summary")
        Picasso.with(this).load(movie.getString("large_cover_image"))
                .placeholder(R.drawable.placeholder_image).into(view_movie_recycler_image)
    }
}