package com.yoochangwons.kotlinmissingcode

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_async.*
import java.lang.Exception

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        var backGroundAsyncTask: BackGroundAsyncTask? = null

        start.setOnClickListener {
            backGroundAsyncTask = BackGroundAsyncTask(progressbar, ment)
            backGroundAsyncTask?.execute()
        }

        stop.setOnClickListener {
            backGroundAsyncTask?.cancel(true)
        }
    }
}

class BackGroundAsyncTask(
    val progressBar: ProgressBar,
    val progressText: TextView
) : AsyncTask<Int, Int, Int>() {
    // result -> onPostExecute 에서 사용할 타입
    // progress -> onProgressUpdate 에서 사용할 타입
    // prams -> doIntBackGround 에서 사용할 타입

    var percent: Int = 0

    override fun onPreExecute() {
        percent = 0
        progressBar.setProgress(percent)
    }

    override fun doInBackground(vararg params: Int?): Int {
        // isCancelled -> AsyncTask 의 내부적으로 있는 함수
        // true -> 취소가 된경우
        // false -> 취소가 되지 않은 경우
        while (isCancelled == false) {
            // 취소가 되지 않았다면 percent 를 올려주겠다
            percent++
            if (percent > 100) break
            // value 값이 onProgressUpdate 에 value 의 들어간다
            else publishProgress(percent)

            // Thread 로 잠재운다
            try {
                Thread.sleep(100)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return percent
    }

    @SuppressLint("SetTextI18n")
    override fun onProgressUpdate(vararg values: Int?) {
        // ? 앞의 있는 것이 null 인 경우에는 0 이다
        progressBar.setProgress(values[0] ?: 0)
        progressText.text = "퍼센트 : " + values[0]
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Int?) {
        progressText.text = "작업이 완료되었습니다."
    }

    // Task 가 인위적으로 취소되었을 때
    override fun onCancelled() {
        progressBar.setProgress(0)
        progressText.text = "작업이 취소되었습니다."
    }
}