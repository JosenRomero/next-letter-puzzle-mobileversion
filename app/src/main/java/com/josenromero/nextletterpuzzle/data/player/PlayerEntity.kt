package com.josenromero.nextletterpuzzle.data.player

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josenromero.nextletterpuzzle.utils.Constants

@Entity(tableName = Constants.player_table)
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo (name = "name") val name: String,
    @ColumnInfo (name = "currentLevel") val currentLevel: Int,
    @ColumnInfo (name = "achievements") var achievements: List<String> = emptyList()
)
