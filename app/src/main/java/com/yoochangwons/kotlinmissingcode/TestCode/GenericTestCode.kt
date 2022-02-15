package com.yoochangwons.kotlinmissingcode.TestCode

class Rectangle<T>(
    private val width: T,
    private val height: T,
) where T : Number, T : Comparable<T> {

    fun getArea(): T {
        return (width.toDouble() * height.toDouble()) as T
    }

    fun compare(): Boolean {
        return width < height
    }
}

fun <T : Comparable<T>> compare(a: T, b: T): Boolean {
    return a < b
}

fun <T> compare2(a: T, b: T): Boolean where T : Comparable<T>, T : Number {
    if (a < b) return true
    return false
}

fun main(args: Array<String>) {

    println(Rectangle(10, 20).getArea())
    println(Rectangle(10, 20).compare())

    println("-------------------------------------")

    println(compare(1, 2))
    println(compare(2, 1))
    println(compare("aaaa", "aaab"))
    println(compare("1", "2"))
    println(compare("cat", "dog"))

    println("-------------------------------------")

    // Number 를 상속 받고 있으므로 String, char 타입으로 설정하면 컴파일 에러
    // println(compare2("1", "2"))
    println(compare2(1, 2))

}