package com.example.ajay3.appwithkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_item.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by ajay3 on 6/30/2017.
 */

class RecyclerAdapter(mActivity: MainActivity) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    val mContext: Context
    val mList: JSONArray

    init {
        mContext = mActivity.applicationContext
        mList = getList()
    }

    @Throws(JSONException::class)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: JSONObject = mList[position] as JSONObject
        holder.mRecyclerText.text = movie.getString("title")
        holder.mRecyclerDesc.text = movie.getString("summary")
        Picasso.with(mContext).load(movie.getString("medium_cover_image"))
                .placeholder(R.drawable.placeholder_image).into(holder.mRecyclerImage)

        // Click Listener
        holder.itemView.setOnClickListener { Toast.makeText(mContext,movie.getString("title"),
                Toast.LENGTH_SHORT).show() }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.length()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mRecyclerText: TextView
        val mRecyclerDesc: TextView
        val mRecyclerImage: ImageView

        init {
            mRecyclerText = itemView.recycler_item_text
            mRecyclerImage = itemView.recycler_image
            mRecyclerDesc = itemView.recycler_desc
        }
    }

    // Create the List
    fun getList(): JSONArray {

        val array: Array<String> = arrayOf(Constants.MOVIE_PAGE1_URL,Constants.MOVIE_PAGE2_URL,
                Constants.MOVIE_PAGE3_URL,Constants.MOVIE_PAGE4_URL,Constants.MOVIE_PAGE5_URL)
        val movieList: JSONArray = JSONArray()

        for(url: String in array){
            val jsonObject: JSONObject = APIService().execute(url).get()

            if(jsonObject.getString("status") == "ok") {
                val list = jsonObject.getJSONObject("data").getJSONArray("movies")
                var temp = 0
                while (temp < list.length()){
                    movieList.put(list.get(temp))
                    temp++
                }
            }else{
                Toast.makeText(mContext,"Couldn't load data",Toast.LENGTH_SHORT).show()
            }
        }

        return movieList
    }
}