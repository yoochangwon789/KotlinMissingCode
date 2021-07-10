package com.yoochangwons.kotlinmissingcode

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.URL

class NetWorkRecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net_work_recycler_view)

    }
}

class NetWorkRecyclerViewTask : AsyncTask<Any?, Any?, Any?>() {

    override fun doInBackground(vararg params: Any?): Any? {
        val urlString = "http://mellowcode.org/json/students/"
        val url : URL = URL(urlString)
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
