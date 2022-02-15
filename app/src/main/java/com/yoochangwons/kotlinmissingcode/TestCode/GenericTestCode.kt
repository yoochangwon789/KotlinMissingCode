package com.yoochangwons.kotlinmissingcode.TestCode

class Rectangle<T>(private val width: T, private val height: T) where T: Number, T: Comparable<T> {

    fun getArea(): T {
        return (width.toDouble() * height.toDouble()) as T
    }

}

fun main(args: Array<String>) {

    print(Rectangle(10, 20).getArea())
}