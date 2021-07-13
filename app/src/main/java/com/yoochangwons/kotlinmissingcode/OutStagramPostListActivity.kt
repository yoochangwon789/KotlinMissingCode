package com.yoochangwons.kotlinmissingcode

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class OutStagramPostListActivity : AppCompatActivity() {

    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_stagram_post_list)

        glide = Glide.with(this)
    }
}

class PostAdapter(
    private val dataSet: ArrayList<Post>,
    private val glide: RequestManager
    ) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postOwner: TextView? = null
        val postImage: ImageView
        var postContent: TextView? = null

        init {
            postOwner = itemView.findViewById(R.id.post_owner)
            postImage = itemView.findViewById(R.id.post_img)
            postContent = itemView.findViewById(R.id.post_content)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.outstagram_item_view, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.postOwner?.text = dataSet[position].owner
        viewHolder.postContent?.text = dataSet[position].content
        glide.load(dataSet[position].image).into(viewHolder.postImage)
    }

    override fun getItemCount() = dataSet.size
}