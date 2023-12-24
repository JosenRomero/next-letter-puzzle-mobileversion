package com.josenromero.nextletterpuzzle.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

class ListConverter {

    @TypeConverter
    fun listToJsonString(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonStringToList(value: String): List<String> {
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }

}
