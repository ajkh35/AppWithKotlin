package com.example.ajay3.appwithkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * Created by ajay3 on 6/27/2017.
 */
class MyAdapter(mActivity: MainActivity) : BaseAdapter() {

    val mList: ArrayList<String>
    val mContext: Context

    init {
        mList = getList()
        mContext = mActivity.applicationContext
    }

    override fun getItem(position: Int): Any {
        return mList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var returnView = convertView
        val itemHolder: ViewHolder
        if(returnView == null){
            returnView = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false)
            itemHolder = ViewHolder(returnView)
            returnView.tag = itemHolder
        }else{
            itemHolder = returnView.tag as ViewHolder
        }
        itemHolder.listItem.text = mList[position]
        itemHolder.listItem.setOnClickListener { Toast.makeText(mContext,"Item #"+(position+1),
                Toast.LENGTH_SHORT).show() }
        return returnView as View
    }

    // Create List Items
    fun getList(): ArrayList<String>{
        var i: Int = 1
        val list: ArrayList<String> = ArrayList()
        while (i < 100){
            list.add("Item #"+i)
            i++
        }
        return list
    }

    class ViewHolder(view: View) {

        val listItem: TextView

        init {
            listItem = view.item_text
        }
    }
}