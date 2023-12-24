package com.josenromero.nextletterpuzzle.data.player

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.josenromero.nextletterpuzzle.utils.ListConverter

@Database(
    entities = [PlayerEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class)
abstract class PlayerDataBase: RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}