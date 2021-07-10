package com.yoochangwons.kotlinmissingcode

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_net_work_recycler_view.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetWorkRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_work_recycler_view)

        val netWorkRecyclerViewTask = NetWorkRecyclerViewTask(net_work_recycler_view, this)
        netWorkRecyclerViewTask.execute()

    }
}

class NetWorkRecyclerViewTask(
    val recyclerView: RecyclerView,
    val context: Context
) : AsyncTask<Any?, Any?, Array<PersonFromServer>?>() {

    override fun onPostExecute(result: Array<PersonFromServer>?) {
        val adapter = NetWorkRecyclerViewAdapter(result!!)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun doInBackground(vararg params: Any?): Array<PersonFromServer>? {
        val urlString = "http://mellowcode.org/json/students/"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")

        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream, "UTF-8"
                )
            )
            buffer = reader.readLine()
        }

        return Gson().fromJson(buffer, Array<PersonFromServer>::class.java)
    }
}

class NetWorkRecyclerViewAdapter(
    private val dataSet: Array<PersonFromServer>
) : RecyclerView.Adapter<NetWorkRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewId: TextView? = null
        var textViewName: TextView? = null
        var textViewAge: TextView? = null
        var textViewIntro: TextView? = null

        init {
            textViewId = itemView.findViewById(R.id.data_id)
            textViewName = itemView.findViewById(R.id.data_name)
            textViewAge = itemView.findViewById(R.id.data_age)
            textViewIntro = itemView.findViewById(R.id.data_intro)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.net_work_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textViewId?.text = dataSet[position].id.toString()
        viewHolder.textViewName?.text = dataSet[position].name
        viewHolder.textViewAge?.text = dataSet[position].age.toString()
        viewHolder.textViewIntro?.text = dataSet[position].intro
    }

    override fun getItemCount() = dataSet.size
}
