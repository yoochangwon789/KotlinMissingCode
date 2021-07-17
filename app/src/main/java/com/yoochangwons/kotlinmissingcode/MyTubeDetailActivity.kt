package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_my_tube_detail_activity.*

class MyTubeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tube_detail_activity)

        val url = intent.getStringExtra("video_url")
        // 영상을 재생하기 위한 mediaController
        val mediaController = MediaController(this)
        video_view.setVideoPath(url)
        video_view.requestFocus()
        video_view.start()
        mediaController.show()
        
        // Exoplayer -> google 에서 제공하는 영상 전문적 플레이어 라이브러리
        // drm -> digital right management -> 영상을 다운로드를 제한 하는 것
        
    }
}