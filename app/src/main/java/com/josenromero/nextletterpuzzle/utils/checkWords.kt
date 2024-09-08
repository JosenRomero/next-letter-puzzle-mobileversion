package com.josenromero.nextletterpuzzle.utils

sealed class Answer(val character: String) {
    object Right: Answer("o")
    object Valid: Answer("+")
    object Wrong: Answer("x")
}

fun checkWords (
    answer: ArrayList<String>,
    validAnswer: ArrayList<String>,
    words: List<String>
): List<String> {

    val arr: MutableList<String> = arrayListOf()
    val rightAnswers: ArrayList<String> = arrayListOf()

    for (i in 0 until answer.size) {
        if ((answer.contains(words[i]) || validAnswer.contains(words[i])) && !rightAnswers.contains(words[i])) {

            if (validAnswer.contains(words[i])) arr.add(Answer.Valid.character)
            else arr.add(Answer.Right.character)

            rightAnswers.add(words[i])
        } else {
            arr.add(Answer.Wrong.character)
        }
    }

    return arr

}