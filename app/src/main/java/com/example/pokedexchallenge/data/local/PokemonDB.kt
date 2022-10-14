package com.example.pokedexchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedexchallenge.data.local.dao.DetailsDao
import com.example.pokedexchallenge.data.local.dao.EntryDao
import com.example.pokedexchallenge.data.local.dao.TypesDao
import com.example.pokedexchallenge.data.local.entity.DetailsEntity
import com.example.pokedexchallenge.data.local.entity.EntryEntity
import com.example.pokedexchallenge.data.local.entity.TypesEntity

@Database(
    entities = [EntryEntity::class, TypesEntity::class, DetailsEntity::class],
    version = 1,
    exportSchema = false
)

abstract class PokemonDB : RoomDatabase() {

    abstract fun getEntryDao(): EntryDao
    abstract fun getTypesDao(): TypesDao
    abstract fun getDetailsDao(): DetailsDao
}