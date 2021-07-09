package com.yoochangwons.kotlinmissingcode

import io.realm.RealmObject

// 필드 작성
open class School : RealmObject() {
    var name: String? = null
    var location: String? = null
}