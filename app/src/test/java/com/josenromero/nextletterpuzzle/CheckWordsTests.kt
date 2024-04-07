package com.josenromero.nextletterpuzzle

import com.josenromero.nextletterpuzzle.utils.checkWords
import org.junit.Assert.assertEquals
import org.junit.Test

class CheckWordsTests {

    @Test
    fun checkWords_allAnswersTheSame() {

        val answer: ArrayList<String> = arrayListOf("pera", "manzana", "kiwi")
        val validAnswer: ArrayList<String> = arrayListOf()
        val words: List<String> = listOf("pera", "pera", "pera")

        val expected = listOf("o", "x", "x")

        val actual = checkWords(answer, validAnswer, words)

        println(actual)

        assertEquals(expected, actual)

    }

    @Test
    fun checkWords_firstLevelComplete() {

        val answer: ArrayList<String> = arrayListOf("pera", "manzana", "kiwi")
        val validAnswer: ArrayList<String> = arrayListOf()
        val words: List<String> = listOf("pera", "manzana", "kiwi")

        val expected = listOf("o", "o", "o")

        val actual = checkWords(answer, validAnswer, words)

        println(actual)

        assertEquals(expected, actual)

    }

}