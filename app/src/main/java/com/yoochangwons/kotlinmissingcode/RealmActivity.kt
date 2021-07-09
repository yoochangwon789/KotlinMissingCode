package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_realm.*

class RealmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)

        // Realm 초기화 작업
        Realm.init(this)
        // Migration -> 데이터 베이스 용어 -> 동기화를 시켜주겠다.
        val config: RealmConfiguration = RealmConfiguration
            .Builder()
            .deleteRealmIfMigrationNeeded()
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
        // Realm 을 얻어오는 방법
        val realm = Realm.getDefaultInstance()

        realm_save.setOnClickListener {
            // realm 을 실행(executeTransaction) -> class 를 가져온다(createObject) -> with 문법으로
            // 코드 간결과 하여 this 키워드 사용
            realm.executeTransaction {
                with(it.createObject(School::class.java)) {
                    this.name = "어떤 대학교"
                    this.location = "서울"
                }
            }
        }
        realm_load.setOnClickListener {
            // realm 을 실행(executeTransaction) -> where 문법으로 원하는 클래스 탐색 -> findFirst 첫번째 데이터 가져온다
            realm.executeTransaction {
                val data = it.where(School::class.java).findFirst()
                Log.d("dataa", " data$data")
            }
        }
        realm_delete.setOnClickListener {
            realm.executeTransaction {
                it.where(School::class.java).findAll().deleteAllFromRealm()
            }
        }
    }
}