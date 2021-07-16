package com.yoochangwons.kotlinmissingcode

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

// API 가 변하는 부분을 관리해 주면 된다
// interface 으로 만드는 이유은 retrofit 의 설명서 양식이기 때문
interface RetrofitService {

    // 원하는 Mapping -> Get 을 만들어주고 함수를 생성 후 원하는 return 타입을 설정
    // call 이라는 제너리타입 안에 return 타입을 설정
    @GET("json/students/")
    fun getStudentsList(): Call<ArrayList<PersonFromServer>>

    @POST("json/students/")
    fun createStudent(
        @Body params: HashMap<String, Any>
    ): Call<PersonFromServer>

    @POST("json/students/")
    fun createStudentEasy(
        @Body person: PersonFromServer
    ): Call<PersonFromServer>

    @POST("user/signup/")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("password1") password1: String,
        @Field("password2") password2: String
    ): Call<User>

    @POST("/user/login/")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password1: String
    ): Call<User>

    @GET("/instagram/post/list/all/")
    fun getAllPosts(): Call<ArrayList<Post>>

    @Multipart
    @POST("/instagram/post/")
    fun upLoadPost(
        @Part image: MultipartBody.Part,
        @Part("content") requestBody: RequestBody
    ): Call<Post>

    @GET("instagram/post/list/")
    fun getUserPostList(): Call<ArrayList<Post>>

    @GET("youtube/list/")
    fun getYoutubeList()
}