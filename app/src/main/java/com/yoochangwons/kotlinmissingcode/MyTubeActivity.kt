package com.yoochangwons.kotlinmissingcode

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_my_tube.*
import kotlinx.android.synthetic.main.activity_out_stagram_my_post_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTubeActivity : AppCompatActivity() {

    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tube)


        (application as MasterApplication).service.getYoutubeList().enqueue(
            object : Callback<ArrayList<YouTube>> {
                override fun onResponse(
                    call: Call<ArrayList<YouTube>>,
                    response: Response<ArrayList<YouTube>>
                ) {
                    if (response.isSuccessful) {
                        glide = Glide.with(this@MyTubeActivity)
                        val youtubeList = response.body()
                        val adapter = MyTubeAdapter(youtubeList!!, glide)
                        my_tube_recycler_view.adapter = adapter
                        my_tube_recycler_view.layoutManager = LinearLayoutManager(this@MyTubeActivity)
                    }
                }

                override fun onFailure(call: Call<ArrayList<YouTube>>, t: Throwable) {
                    Log.d("1234", "error")
                }

            })
    }
}

class MyTubeAdapter(
    private val youtubeList: ArrayList<YouTube>,
    private val glide: RequestManager,
    val activity: Activity
) : RecyclerView.Adapter<MyTubeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView? = null
        val thumbnail: ImageView
        var content: TextView? = null

        init {
            title = itemView.findViewById(R.id.youtube_title)
            thumbnail = itemView.findViewById(R.id.youtube_thumbnail)
            content = itemView.findViewById(R.id.youtube_content)

            itemView.setOnClickListener {
                activity
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.youtube_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.title?.text = youtubeList[position].title
        viewHolder.content?.text = youtubeList[position].content
        glide.load(youtubeList[position].thumbnail).into(viewHolder.thumbnail)
    }

    override fun getItemCount() = youtubeList.size
}