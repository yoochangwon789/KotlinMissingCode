package com.yoochangwons.kotlinmissingcode

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 여럿 Activity 가 이 클래스를 사용하고 싶을 때 Application 을 상속 받아 onCreate 구현 을 해준다
// Activity 보다 Application 이 상위 단계의 있다
// Manifest 를 보면 참고 할 수 있다
// Application 은 Activity 가 실행되기 전에 먼저 실행 되고 그 후에 Activity 가 실행된다
class MasterApplication : Application() {

    lateinit var service: RetrofitService

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        createRetrofit()
    }

    fun createRetrofit() {
        // Interceptor -> 통신을 가로 채는 기능 으로 응답된 데이터를 가로채고
        // -> 가로챈 데이터에 Header 를 입힌다 -> 그리고 빌드 해주고
        // -> 다시 가로챈 데이터를 보내준다
        val header = Interceptor {
            val original = it.request()
            if (checkIsLogIn()) {
                getUserToken()?.let { token ->
                    // Login 이 되었을 때
                    val request = original.newBuilder()
                        .header("Authorization", "token " + token)
                        .build()
                    it.proceed(request)
                }
            } else {
                // Login 이 되어있지 않을 때
                it.proceed(original)
            }
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(header)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        service = retrofit.create(RetrofitService::class.java)
    }

    @SuppressLint("CommitPrefEdits")
    private fun checkIsLogIn(): Boolean {
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val token = sp.getString("login_sp", "null")

        return token != "null"
    }

    private fun getUserToken(): String? {
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val token = sp.getString("login_sp", "null")

        return if (token == "null") null
        else token
    }
}