package com.yoochangwons.kotlinmissingcode

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
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
import kotlinx.android.synthetic.main.activity_apple.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class AppleActivity : AppCompatActivity() {

    lateinit var glide: RequestManager
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apple)

        glide = Glide.with(this)

        (application as MasterApplication).service.getSongList().enqueue(
            object : Callback<ArrayList<Song>> {
                override fun onResponse(
                    call: Call<ArrayList<Song>>,
                    response: Response<ArrayList<Song>>
                ) {
                    if (response.isSuccessful) {
                        val songList = response.body()
                        val adapter = MelonAdapter(songList!!, glide, this@AppleActivity)
                        apple_recycler_view.adapter = adapter
                        apple_recycler_view.layoutManager = LinearLayoutManager(this@AppleActivity)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Song>>, t: Throwable) {
                }
            })
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

                play.setOnClickListener {
                    val position = adapterPosition
                    val path = songList[position].song

                    try {
                        mediaPlayer?.stop()
                        // 음악 영상은 핸드폰 리소스를 많이 차지 하는데 리소스를 놓아주기 위하여 release 를 많이 사용한다
                        mediaPlayer?.release()
                        mediaPlayer = null
                        mediaPlayer = MediaPlayer.create(this@AppleActivity, Uri.parse(path))
                        mediaPlayer?.start()
                    } catch (e: Exception) {
                        Log.d("eee" , "Exception : $e")
                    }
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