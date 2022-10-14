package com.example.pokedexchallenge.data.local.dao

import androidx.room.*
import com.example.pokedexchallenge.data.local.entity.EntryEntity

@Dao
interface EntryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<EntryEntity>)

    @Query("SELECT * from entryentity")
    suspend fun findAll(): List<EntryEntity>

    @Query("SELECT * FROM entryentity WHERE id=:pokemonId")
    suspend fun find(pokemonId: Int): EntryEntity

    @Query("SELECT * FROM entryentity WHERE isFavorite")
    suspend fun findAllFavorites(): List<EntryEntity>

    @Update
    suspend fun update(entryEntity: EntryEntity)
}