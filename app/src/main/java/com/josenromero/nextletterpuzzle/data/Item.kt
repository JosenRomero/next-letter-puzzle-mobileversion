package com.josenromero.nextletterpuzzle.data

import com.google.gson.annotations.SerializedName

data class Item (
    @SerializedName("topic") val topic: String,
    @SerializedName("letters") val letters: ArrayList<String>,
    @SerializedName("answer") val answer: ArrayList<String>,
    @SerializedName("validAnswer") val validAnswer: ArrayList<String>
)