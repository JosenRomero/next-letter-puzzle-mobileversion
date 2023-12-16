package com.josenromero.nextletterpuzzle.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.josenromero.nextletterpuzzle.data.Item

fun getData(context: Context, path: String): List<Item> {

    val jsonString: String = try {
        context.assets.open(path)
            .bufferedReader()
            .use { it.readText() }
    } catch (e: Exception) {
        "[]"
    }

    val listDataType = object : TypeToken<List<Item>>() {}.type

    return Gson().fromJson(jsonString, listDataType)

}