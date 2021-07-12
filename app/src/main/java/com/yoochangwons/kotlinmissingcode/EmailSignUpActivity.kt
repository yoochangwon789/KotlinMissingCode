package com.yoochangwons.kotlinmissingcode

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_email_sign_up.*

class EmailSignUpActivity : AppCompatActivity() {

    lateinit var userNameView: EditText
    lateinit var userPassword1View: EditText
    lateinit var userPassword2View: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sign_up)

        initView(this)
    }

    fun initView(activity: Activity) {
        userNameView = activity.findViewById(R.id.user_name_input_box)
        userPassword1View = activity.findViewById(R.id.password1_input_box)
        userPassword2View = activity.findViewById(R.id.password2_input_box)
    }
}