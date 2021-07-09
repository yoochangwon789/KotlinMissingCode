package com.yoochangwons.kotlinmissingcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmConfiguration

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
            .build()
        Realm.setDefaultConfiguration(config)
        // Realm 을 얻어오는 방법
        val ream = Realm.getDefaultInstance()
    }
}