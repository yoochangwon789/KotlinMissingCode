package com.yoochangwons.kotlinmissingcode

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

    classTestCode1.id = 1
    classTestCode1.age = 26
    classTestCode1.name = "유창원"

    classTestCode2.name = "신예은"

    println(classTestCode1.name)
    println(classTestCode2.name)
    println(classTestCode3.name)
}