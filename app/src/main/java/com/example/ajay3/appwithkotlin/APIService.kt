package com.example.ajay3.appwithkotlin

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.Charset

/**
 * Created by ajay3 on 6/30/2017.
 */
class APIService(context: Context) : AsyncTask<String, Int, JSONObject>() {

    var mProgressDialog: ProgressDialog
    val mContext = context

    init {
        mProgressDialog = ProgressDialog(mContext)
        mProgressDialog.max = 100
        mProgressDialog.progress = 0
    }

//    override fun onPreExecute() {
//        mProgressDialog.show(mContext,"","")
//        super.onPreExecute()
//    }
//
//    override fun onProgressUpdate(vararg values: Int?) {
//        mProgressDialog.progress = values[0] as Int
//    }

    override fun doInBackground(vararg params: String?): JSONObject {
        val jsonObject: JSONObject = readJSONFromURL(params[0] as String)
        return jsonObject
    }

    override fun onPostExecute(result: JSONObject?) {
//        mProgressDialog.dismiss()
        super.onPostExecute(result)
    }

    // Get JSONObject from URL
    @Throws(JSONException::class)
    fun readJSONFromURL(Url: String): JSONObject{
        val jsonObject: JSONObject
        val inputStream: InputStream = URL(Url).openStream()
        val reader: BufferedReader = BufferedReader(InputStreamReader(inputStream,Charset.forName("UTF-8")))
        val jsonText: String = readAll(reader)
        jsonObject = JSONObject(jsonText)
        return jsonObject
    }

    // Get String from Stream Data
    fun readAll(reader: BufferedReader): String {
        val builder: StringBuilder = StringBuilder()
        var charPosition: Int

        do {
            charPosition = reader.read()
            builder.append(charPosition.toChar())
        }while(charPosition != -1)

        return builder.toString()
    }

}