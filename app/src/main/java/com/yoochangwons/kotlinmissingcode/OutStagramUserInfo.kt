package com.yoochangwons.kotlinmissingcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_out_stagram_user_info.*

class OutStagramUserInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_stagram_user_info)

        all_list.setOnClickListener {
            startActivity(
                Intent(this, OutStagramPostListActivity::class.java)
            )
        }

        my_list.setOnClickListener {
            startActivity(
                Intent(this, OutStagramMyPostListActivity::class.java)
            )
        }

        upload.setOnClickListener {
            startActivity(
                Intent(this, OutStagramUploadActivity::class.java)
            )
        }
    }
}