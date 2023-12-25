package com.josenromero.nextletterpuzzle.di

import android.content.Context
import androidx.room.Room
import com.josenromero.nextletterpuzzle.data.player.PlayerDao
import com.josenromero.nextletterpuzzle.data.player.PlayerDataBase
import com.josenromero.nextletterpuzzle.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesPlayerRoomDatabase(@ApplicationContext app: Context): PlayerDataBase {

        // create database
        return Room.databaseBuilder(
            app,
            PlayerDataBase::class.java,
            Constants.player_database
        ).build()

    }

    @Provides
    @Singleton
    fun providesPlayerDao(playerDatabase: PlayerDataBase): PlayerDao {
        return playerDatabase.playerDao()
    }
}