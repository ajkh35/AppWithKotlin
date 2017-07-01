package com.example.ajay3.appwithkotlin

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.OnScrollListener
import org.json.JSONArray

/**
 * Created by ajay3 on 7/1/2017.
 */
class MyScrollListener(layoutManager: GridLayoutManager,onLoadMore: MainActivity.MyListener,
                       activity: MainActivity,list: JSONArray) : OnScrollListener() {

    val visibleThreshold = 2
    val mLayoutManager = layoutManager
    val mLoadMore = onLoadMore
    var mScrolled = false
    val mContext = activity.applicationContext!!
    val mList = list

    override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if(newState == RecyclerView.SCROLL_STATE_DRAGGING){
            mScrolled = true
        }
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = mLayoutManager.itemCount
        val lastVisibleItem = mLayoutManager.findLastVisibleItemPosition()
        if(mScrolled && totalItemCount <= (lastVisibleItem + visibleThreshold)){
            mScrolled = false
            mLoadMore.onLoadMore(mContext,mList)
        }
    }
}