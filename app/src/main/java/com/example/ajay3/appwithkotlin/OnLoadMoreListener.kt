package com.example.ajay3.appwithkotlin

import android.content.Context
import org.json.JSONArray

/**
 * Created by ajay3 on 7/1/2017.
 */
interface OnLoadMoreListener{

    fun onLoadMore(context: Context,list: JSONArray)
}