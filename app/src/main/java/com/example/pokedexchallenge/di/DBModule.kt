package com.example.pokedexchallenge.di

import android.content.Context
import androidx.room.Room
import com.example.pokedexchallenge.commons.Constants.DB_NAME
import com.example.pokedexchallenge.data.local.PokemonDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideDB(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        PokemonDB::class.java,
        DB_NAME
    ).build()

    @Provides
    @Singleton
    fun provideEntryDao(db: PokemonDB) = db.getEntryDao()

    @Provides
    @Singleton
    fun provideTypesDAO(db: PokemonDB) = db.getTypesDao()

    @Provides
    @Singleton
    fun provideDetailsDAO(db: PokemonDB) = db.getDetailsDao()
}