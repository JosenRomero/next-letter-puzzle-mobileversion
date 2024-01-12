package com.josenromero.nextletterpuzzle.utils

fun checkWords (
    answer: ArrayList<String>,
    validAnswer: ArrayList<String>,
    words: List<String>
): List<String> {

    val arr: MutableList<String> = arrayListOf()
    val rightAnswers: ArrayList<String> = arrayListOf()

    for (i in 0 until answer.size) {
        if ((answer.contains(words[i]) || validAnswer.contains(words[i])) && !rightAnswers.contains(words[i])) {
            arr.add("o")
            rightAnswers.add(words[i])
        } else {
            arr.add("x")
        }
    }

    return arr

}