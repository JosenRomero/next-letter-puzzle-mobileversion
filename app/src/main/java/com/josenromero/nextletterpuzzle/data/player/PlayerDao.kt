package com.josenromero.nextletterpuzzle.data.player

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.josenromero.nextletterpuzzle.utils.Constants

// DAO - Data Access Object
@Dao
interface PlayerDao {

    @Query("SELECT * FROM ${Constants.player_table}")
    fun getPlayers(): List<PlayerEntity>

    @Insert
    fun addOnePlayer(player: PlayerEntity)

    @Delete
    fun deleteOnePlayer(player: PlayerEntity)

    @Update
    fun updateOnePlayer(player: PlayerEntity)

}