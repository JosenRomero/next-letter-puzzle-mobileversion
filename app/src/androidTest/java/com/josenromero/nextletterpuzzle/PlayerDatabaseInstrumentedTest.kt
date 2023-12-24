package com.josenromero.nextletterpuzzle

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.josenromero.nextletterpuzzle.data.player.PlayerDao
import com.josenromero.nextletterpuzzle.data.player.PlayerDataBase
import com.josenromero.nextletterpuzzle.data.player.PlayerEntity
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PlayerDatabaseInstrumentedTest {

    private lateinit var playerDao: PlayerDao
    private lateinit var db: PlayerDataBase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, PlayerDataBase::class.java).build()
        playerDao = db.playerDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun writeAndReadPlayerInfo() {

        // instance PlayerEntity
        val name = "Jose Romero"
        val currentLevel = 9
        val myPlayer = PlayerEntity(0, name, currentLevel)

        // add data in Database
        playerDao.addOnePlayer(player = myPlayer)
        val allPlayers = playerDao.getPlayers()
        playerDao.deleteOnePlayer(player = myPlayer)

        println(allPlayers)

        // check myPlayer in datababase
        assertEquals(allPlayers[0].currentLevel, currentLevel)

    }

}