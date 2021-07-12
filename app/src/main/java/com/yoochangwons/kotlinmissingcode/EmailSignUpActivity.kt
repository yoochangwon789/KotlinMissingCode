package com.yoochangwons.kotlinmissingcode

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sign_up)

        initView(this)
        setUpListener(this)
    }

    fun initView(activity: Activity) {
        userNameView = activity.findViewById(R.id.user_name_input_box)
        userPassword1View = activity.findViewById(R.id.password1_input_box)
        userPassword2View = activity.findViewById(R.id.password2_input_box)
        registerBtn = activity.findViewById(R.id.register)
    }

    fun setUpListener(activity: Activity) {
        registerBtn.setOnClickListener {
            register(activity)
        }
    }

    fun register(activity: Activity) {
        val userName = userNameView.text.toString()
        val password1 = userPassword1View.text.toString()
        val password2 = userPassword2View.text.toString()
        val register = Register(userName, password1, password2)

        (application as MasterApplication).service.register(register).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Toast.makeText(activity, "가입에 성공 하였습니다.", Toast.LENGTH_LONG).show()
                val user = response.body()
                val token = user?.token
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