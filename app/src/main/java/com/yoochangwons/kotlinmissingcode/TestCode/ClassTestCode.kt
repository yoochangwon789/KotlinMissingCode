package com.yoochangwons.kotlinmissingcode.TestCode

private class ClassTestCode() {
    var id: Int? = null
    var age: Int? = null
    var name: String? = null

    constructor(id: Int, age: Int) : this() {
        this.id = id
        this.age = age
    }

    constructor(id: Int, age: Int, name: String) : this() {
        this.id = id
        this.age = age
        this.name = name
    }
}

fun main(array: Array<String>) {

    val classTestCode1 = ClassTestCode()
    val classTestCode2 = ClassTestCode(1, 2)
    val classTestCode3 = ClassTestCode(1, 2, "IU")

    classTestCode1.name = "유창원"

    classTestCode2.name = "신예은"

    println(classTestCode1.name)
    println(classTestCode2.name)
    println(classTestCode3.name)

    classTestCode1.age = 10

    // age 가 null 이 아니라면 let 코드를 출력
    classTestCode1.age?.let { classTestCode1.age = 30 }
    println(classTestCode1.age)
}