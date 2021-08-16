package com.yoochangwons.kotlinmissingcode.Programers

fun main(args: Array<String>) {

    var s = "one4seveneight"

    val solution = Solution()
    print(solution.solution(s))
}

class Solution {
    fun solution(s: String): Int {
        var answerString: String = s
        var answer: Int = 0
        val mapList = mutableMapOf<String, String>()

        mapList["zero"] = "0"
        mapList["one"] = "1"
        mapList["two"] = "2"
        mapList["three"] = "3"
        mapList["four"] = "4"
        mapList["five"] = "5"
        mapList["six"] = "6"
        mapList["seven"] = "7"
        mapList["eight"] = "8"
        mapList["nine"] = "9"

        mapList.forEach {
            answerString = answerString.replace(it.key, it.value)
        }

        answer = answerString.toInt()

        return answer
    }
}