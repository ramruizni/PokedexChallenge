package com.example.pokedexchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedexchallenge.data.local.entity.DetailsEntity

@Dao
interface DetailsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(details: DetailsEntity)

    @Query("SELECT * from detailsentity WHERE pokemonId = :pokemonId")
    suspend fun findByPokemonId(pokemonId: Int): DetailsEntity?
}