package com.yoochangwons.kotlinmissingcode

import android.app.Application

// 여럿 Activity 가 이 클래스를 사용하고 싶을 때 Application 을 상속 받아 onCreate 구현 을 해준다
// Activity 보다 Application 이 상위 단계의 있다
// Manifest 를 보면 참고 할 수 있다
// Application 은 Activity 가 실행되기 전에 먼저 실행 되고 그 후에 Activity 가 실행된다
class MasterApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}