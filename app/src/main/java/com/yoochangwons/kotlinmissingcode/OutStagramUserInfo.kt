package com.yoochangwons.kotlinmissingcode

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_out_stagram_user_info.*

class OutStagramUserInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_stagram_user_info)

        val sp = getSharedPreferences("userId", Context.MODE_PRIVATE)
        val userId = sp.getString("userId", "null")
        user_info_user_id.text = userId

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

        logout_btn.setOnClickListener {
            // logout 시 토큰 값은 "null" 값
            val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("login_sp", "null")
            editor.apply()

            // logout 되었을 시 retrofit 다시 설정 header 에 있는 인증정보를 초기화 시키고 로그인 페이지로 이동
            (application as MasterApplication).createRetrofit()
            finish()
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }
    }
}