package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.Person
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        // baseUrl -> 변하지 않는 URL 부분을 입력
        // addConverterFactory -> 원하는 데이터 타입을 바꾸기 위한 converter 기능능
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // retrofit 에 service 생성 작업
        val service = retrofit.create(RetrofitService::class.java)

        // enqueue 의 CallBack 함수를 통해 응답을 받는다
        // GET 요청
        service.getStudentsList().enqueue(object : Callback<ArrayList<PersonFromServer>> {
            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                // 통신이 잘 된 경우
                if (response.isSuccessful) {
                    // 데이터를 가져와 읽는 코드 1줄로 가능
                    val personList = response.body()
                    Log.d("retrofitt" , "res : " + personList?.get(0)?.age)

                    val code = response.code()
                    Log.d("retrofitt" , "code : $code")

                    val error = response.errorBody()
                    val header = response.headers()
                    Log.d("retrofitt" , "header : $header")
                }
            }

            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {
                Log.d("retrofitt" , "ERROR")
            }
        })

        // POST 요청
//        val params = HashMap<String, Any>()
//        params.put("name", "아이유")
//        params.put("age", 20)
//        params.put("intro", "안녕하세요")
//        service.createStudent(params).enqueue(object : Callback<PersonFromServer> {
//            override fun onResponse(
//                call: Call<PersonFromServer>,
//                response: Response<PersonFromServer>
//            ) {
//                if (response.isSuccessful) {
//                    val person = response.body()
//                    Log.d("retrofitt", "name : ${person?.name}")
//                }
//            }
//
//            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
//            }
//        })

        // POST 요청 2
        val person = PersonFromServer(name = "신예은", age = 20, intro = "안녕하세요 신예은 입니다")
        service.createStudentEasy(person).enqueue(object : Callback<PersonFromServer> {
            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if (response.isSuccessful) {
                    val personBody = response.body()
                    Log.d("retrofitt", "name : ${personBody?.name}")
                }
            }

            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
            }
        })
    }
}