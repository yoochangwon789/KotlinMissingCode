package com.yoochangwons.kotlinmissingcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register.setOnClickListener {
            val intent = Intent(this, EmailSignUpActivity::class.java)
            startActivity(intent)
        }

        login_btn.setOnClickListener {
            val username = user_name_input_box.text.toString()
            val password = password_input_box.text.toString()

            (application as MasterApplication).service.login(
                username, password
            ).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    val user = response.body()
                    val token = user!!.token
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                }
            })
        }
    }
}