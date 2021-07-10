package com.yoochangwons.kotlinmissingcode

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetWorkTask().execute()
    }
}

class NetWorkTask() : AsyncTask<Any?, Any?, Any,>() {

    override fun doInBackground(vararg params: Any?): Any? {
        val urlString: String = "http://mellowcode.org/json/student"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        // Header 작성
        // Key -> Content-Type
        // value -> application/json
        connection.setRequestProperty("Content-Type", "application/json")

        // NetWork 는 비동기 처리 방식으로 진행해야 한다
        // Why? 메인 쓰레드 Exception 발생 때문
        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            Log.d("connn", "inputstream : ${connection.inputStream}")
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream, "UTF-8"
                )
            )
            buffer = reader.readLine()
        }
        return null
    }
}