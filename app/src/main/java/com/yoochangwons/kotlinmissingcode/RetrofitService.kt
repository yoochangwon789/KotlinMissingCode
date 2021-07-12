package com.yoochangwons.kotlinmissingcode

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// API 가 변하는 부분을 관리해 주면 된다
// interface 으로 만드는 이유은 retrofit 의 설명서 양식이기 때문
interface RetrofitService {
    
    // 원하는 Mapping -> Get 을 만들어주고 함수를 생성 후 원하는 return 타입을 설정
    // call 이라는 제너리타입 안에 return 타입을 설정
    @GET("json/students/")
    fun getStudentsList(): Call<ArrayList<PersonFromServer>>

    @POST("json/students/")
    fun createStudent(
        @Body params : HashMap<String, Any>
    ) : Call<PersonFromServer>
}