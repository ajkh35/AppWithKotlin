package com.example.ajay3.appwithkotlin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.progress_bar.view.*
import kotlinx.android.synthetic.main.recycler_item.view.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by ajay3 on 6/30/2017.
 */

class RecyclerAdapter(recyclerView: RecyclerView, activity: MainActivity)
                                            : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mContext = activity.applicationContext!!
    val mActivity = activity
    val mList = getInitialList()
    val layoutManager = recyclerView.layoutManager as GridLayoutManager
    var mLoadMoreListener: Any? = null
    var mPage = 2
    val mRecyclerView = recyclerView
    var mScrollListener: Any? = null
//    val mProgressDialog = ProgressDialog(mContext)

    @Throws(JSONException::class)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MovieViewHolder ){
            val movie = mList[position] as JSONObject
            holder.mRecyclerText.text = movie.getString("title")
            holder.mRecyclerDesc.text = movie.getString("summary")
            Picasso.with(mContext).load(movie.getString("medium_cover_image"))
                    .placeholder(R.drawable.placeholder_image).into(holder.mRecyclerImage)

            // Click Listener
            holder.itemView.setOnClickListener {
                val intent = Intent(mContext, ViewMovie::class.java)
                val bundle = Bundle()
                bundle.putString("MOVIE",movie.toString())
                intent.putExtras(bundle)
                intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                mContext.startActivity(intent)
            }
        }else if(holder is ProgressViewHolder){
            holder.mProgressBar.isIndeterminate = true
        }else{ }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val view: View
        if(viewType == Constants.VIEW_TYPE_MOVIE){
            view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item,parent,false)
            return MovieViewHolder(view)
        }else if(viewType == Constants.VIEW_TYPE_LOADING){
            view = LayoutInflater.from(mContext).inflate(R.layout.progress_bar,parent,false)
            return ProgressViewHolder(view)
        }
        return null
    }

    override fun getItemCount(): Int {
        return mList.length()
    }

    override fun getItemViewType(position: Int): Int {
        if(mList.get(position) == null){
            return Constants.VIEW_TYPE_LOADING
        }else{
            return Constants.VIEW_TYPE_MOVIE
        }
    }

    /**
     * Movie MovieViewHolder class
     */
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mRecyclerText: TextView = itemView.recycler_item_text
        val mRecyclerDesc: TextView = itemView.recycler_desc
        val mRecyclerImage: ImageView = itemView.recycler_image
    }

    /**
     * ProgressBar ViewHolder class
     */
    class ProgressViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val mProgressBar: ProgressBar = itemView.progressBar
    }

    /**
     * Add listener for when to load more
     */
    fun setMyScrollListener(){
        mScrollListener = MyScrollListener(layoutManager,
                mLoadMoreListener as MainActivity.MyListener,mActivity,mList)
        mRecyclerView.addOnScrollListener(mScrollListener as MyScrollListener)
    }

    /**
     * Add listener for loading more items when load more
     */
    fun setOnLoadMoreListener(onLoadMoreListener: MainActivity.MyListener){
        mLoadMoreListener = onLoadMoreListener
    }

    /**
     * Create initial movies list
     */
    fun getInitialList(): JSONArray{
        val jsonObject = APIService(mContext).execute(Constants.MOVIE_URL).get()
        val movieList = jsonObject.getJSONObject("data").getJSONArray("movies")
        return movieList
    }

    /**
     * Set page count for movies list
     */
    fun setPage(pageCount: Int){
        mPage = pageCount
    }
}