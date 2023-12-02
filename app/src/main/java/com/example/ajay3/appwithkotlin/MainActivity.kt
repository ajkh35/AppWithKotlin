package com.example.ajay3.appwithkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    companion object {
        var mRecyclerAdapter: RecyclerAdapter? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.layoutManager = GridLayoutManager(this,2)
        mRecyclerAdapter = RecyclerAdapter(recycler,this)
        recycler.adapter = mRecyclerAdapter as RecyclerAdapter

        //(mRecyclerAdapter as RecyclerAdapter).setOnLoadMoreListener(MyListener())
        //(mRecyclerAdapter as RecyclerAdapter).setMyScrollListener()
    }

    /**
     * Listener class for adding more items
     */
    class MyListener : OnLoadMoreListener{

        val mAdapter = (mRecyclerAdapter as RecyclerAdapter)
        var mCurrentList = JSONArray()

        override fun onLoadMore(context: Context,list: JSONArray) {
            if(list.length() <= 200) {
                val jsonObject = APIService(context)
                        .execute(Constants.MOVIE_PAGE_URL+mAdapter.mPage).get()
                mCurrentList = jsonObject.getJSONObject("data").getJSONArray("movies")
                var i = 0
                while (i < mCurrentList.length()){
                    mAdapter.mList.put(mCurrentList.get(i))
                    i++
                }
                mAdapter.notifyDataSetChanged()
                mAdapter.setPage(mAdapter.mPage+1)
            }else{
                Toast.makeText(context,"All Loaded.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}