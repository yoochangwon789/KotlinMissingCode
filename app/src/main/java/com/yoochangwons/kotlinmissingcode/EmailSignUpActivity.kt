package com.yoochangwons.kotlinmissingcode

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_email_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailSignUpActivity : AppCompatActivity() {

    lateinit var userNameView: EditText
    lateinit var userPassword1View: EditText
    lateinit var userPassword2View: EditText
    lateinit var registerBtn: TextView
    lateinit var loginBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 로그인 되어 있는 경우 바로 PostActivity 로 보내주면 된다
        // checkIsLogIn 이 true 인 경우
        if ((application as MasterApplication).checkIsLogIn()) {
            // 로그인이 되었을 경우
            finish()
            startActivity(
                Intent(this, OutStagramPostListActivity::class.java)
            )
        } else {
            // 로그인이 되어있지 않을 경우
            setContentView(R.layout.activity_email_sign_up)

            initView(this)
            setUpListener(this)
        }
    }

    fun initView(activity: Activity) {
        userNameView = activity.findViewById(R.id.user_name_input_box)
        userPassword1View = activity.findViewById(R.id.password1_input_box)
        userPassword2View = activity.findViewById(R.id.password2_input_box)
        registerBtn = activity.findViewById(R.id.register)
        loginBtn = activity.findViewById(R.id.login_btn)
    }

    @SuppressLint("CommitPrefEdits")
    fun setUpListener(activity: Activity) {
        registerBtn.setOnClickListener {
            register(activity)
        }
        loginBtn.setOnClickListener {
            startActivity(
                Intent(activity, LoginActivity::class.java)
            )
        }
    }

    fun register(activity: Activity) {
        val userName = getUserName()
        val password1 = getUserPassword1()
        val password2 = getUserPassword2()

        (application as MasterApplication).service.register(userName, password1, password2)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(activity, "가입에 성공 하였습니다.", Toast.LENGTH_LONG).show()
                        val user = response.body()
                        val token = user!!.token!!
                        saveToken(token, activity)
                        (application as MasterApplication).createRetrofit()
                        activity.startActivity(
                            Intent(activity, OutStagramPostListActivity::class.java)
                        )
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(activity, "가입에 실패 했습니다.", Toast.LENGTH_LONG).show()
                }
            })
    }

    @SuppressLint("CommitPrefEdits")
    fun saveToken(token: String, activity: Activity) {
        val sp = activity.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sp.edit()
        editor.putString("login_sp", token)
        editor.apply()
    }

    fun getUserName(): String {
        return userNameView.text.toString()
    }

    fun getUserPassword1(): String {
        return userPassword1View.text.toString()
    }

    fun getUserPassword2(): String {
        return userPassword2View.text.toString()
    }
}