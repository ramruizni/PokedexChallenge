package com.example.pokedexchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedexchallenge.data.local.entity.TypesEntity

@Dao
interface TypesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<TypesEntity>)

    @Query("SELECT * from typesentity")
    suspend fun findAll(): List<TypesEntity>
}