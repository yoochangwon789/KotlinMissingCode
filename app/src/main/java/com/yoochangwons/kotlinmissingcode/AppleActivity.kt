package com.yoochangwons.kotlinmissingcode

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager

class AppleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apple)
    }

    inner class MelonAdapter(
        private val songList: ArrayList<Song>,
        private val glide: RequestManager,
        val activity: Activity
    ) : RecyclerView.Adapter<MelonAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var title: TextView? = null
            val thumbnail: ImageView
            val play: ImageView

            init {
                title = itemView.findViewById(R.id.song_title)
                thumbnail = itemView.findViewById(R.id.song_img)
                play = itemView.findViewById(R.id.song_play)

                itemView.setOnClickListener {
                    val position = adapterPosition
                }
            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.song_item_view, viewGroup, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.title?.text = songList[position].title
            glide.load(songList[position].thumbnail).into(viewHolder.thumbnail)
        }

        override fun getItemCount() = songList.size
    }
}