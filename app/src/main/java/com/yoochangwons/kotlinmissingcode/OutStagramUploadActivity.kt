package com.yoochangwons.kotlinmissingcode

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import kotlinx.android.synthetic.main.activity_out_stagram_upload.*
import kotlinx.android.synthetic.main.activity_out_stagram_upload.all_list
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class OutStagramUploadActivity : AppCompatActivity() {

    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_out_stagram_upload)

        view_pictures.setOnClickListener {
            getPicture()
        }

        view_upload.setOnClickListener {
            uploadPost()
        }

        all_list.setOnClickListener {
            startActivity(
                Intent(this, OutStagramPostListActivity::class.java)
            )
        }

        my_list.setOnClickListener {
            startActivity(
                Intent(this, OutStagramMyPostListActivity::class.java)
            )
        }

        user_info.setOnClickListener {
            startActivity(
                Intent(this, OutStagramUserInfo::class.java)
            )
        }
    }

    // 사진을 가져오는 함수
    fun getPicture() {
        // intent 를 보내주고
        // MediaStore 를 이용해 외부 저장소로 이동
        val intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        // 이미지를 선택했었을 시에 그 이미지의 결과를 받아야하기 때문에 사용
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            // uri 어떤 자료의 위치를 나타냄
            // 실제 파일의 위치를 표시하지는 않음
            // 상태 경로
            val uri: Uri = data!!.data!!
            filePath = getImageFilePath(uri)
            Log.d("pathh", "path : $filePath")
        }
    }

    // 이미지의 경로를 알아내는 함수
    // 실제파일의 위치를 찾아야 하는 작업
    // 절대경로
    @SuppressLint("Recycle")
    fun getImageFilePath(contentUri: Uri): String {
        var columnIndex = 0
        // projection -> MediaStore.Images.Media.DATA 이것으로 걸러내겠다
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        // contentResolver-> content 관리하는 거고 query 로 검색하는 것
        // 상태 경로를 contentResolver 를 통해 절대 경로를 알려주는 것
        val cursor = contentResolver.query(contentUri, projection, null, null, null)
        // cursor 를 첫번째로 이동시킨다
        if (cursor!!.moveToFirst()) {
            // columnIndex -> getColumnIndexOrThrow 의 MediaStore.Images.Media.DATA 을 넣어준다
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }

        return cursor.getString(columnIndex)
    }

    fun uploadPost() {
        // 파일의 주소를 넣어주면 파일화 시킨다
        val file = File(filePath)
        // RequestBody 를 create(내가 원하는 타입을 넣어준다, 뒤에는 파일을 넣어준다)
        // 즉 이미지 파일의 주소를 받아 파일화 시켜주고 이미지 타입을 넣어주고 파일도 같이 body 에 작성한다
        val fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val part = MultipartBody.Part.createFormData("image", file.name, fileRequestBody)
        val content = RequestBody.create(MediaType.parse("text/plain"), getContent())

        (application as MasterApplication).service.upLoadPost(
            part, content
        ).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    Log.d("pathh", post!!.content!!)
                    finish()
                    startActivity(Intent(this@OutStagramUploadActivity, OutStagramMyPostListActivity::class.java))
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
            }
        })
    }

    fun getContent(): String {
        return content_input.text.toString()
    }
}