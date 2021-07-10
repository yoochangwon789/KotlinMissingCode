package com.yoochangwons.kotlinmissingcode

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
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
        val urlString: String = "http://mellowcode.org/json/students/"
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

            // BufferedReader -> 한번에 읽어준다 -> Buffer 을 사용해야 컴퓨터가 빠르게 처리 가능
            val reader = BufferedReader(
                // InputStreamReader -> 입력 흐름을 읽을 수 있다
                // 인자로 connection 의 입력 스트림을 가져오고, connection.inputStream 을 읽겠다 는 의미
                InputStreamReader(
                    connection.inputStream, "UTF-8"
                )
            )

            buffer = reader.readLine()
            Log.d("connn", "inputstream : $buffer")
        }

        // Gson 라이브러리로 json 데이터를 객체로 받기 위한 작업
        // But json 데이터를 받을 때 List 타입으로 들어와서 에러가 발생!!
        // 타입을 변경해야 한다.
        val data = Gson().fromJson(buffer, Array<PersonFromServer>::class.java)
        Log.d("connn", "data : $data")


        return null
    }
}