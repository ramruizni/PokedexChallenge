package com.example.pokedexchallenge.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pokedexchallenge.data.local.PokemonDB
import com.example.pokedexchallenge.data.mapper.toEntity
import com.example.pokedexchallenge.domain.model.Types
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class TypesDaoTest {
    private lateinit var db: PokemonDB
    private lateinit var dao: TypesDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PokemonDB::class.java
        ).allowMainThreadQueries().build()
        dao = db.getTypesDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    private val entity1 = Types(
        pokemonId = 1,
        nameSlot1 = "Fire",
        nameSlot2 = "Electric"
    ).toEntity()

    private val entity2 = Types(
        pokemonId = 2,
        nameSlot1 = "Fire",
        nameSlot2 = "Electric"
    ).toEntity()

    @Test
    fun insertsMultipleEntitiesCorrectly() = runTest {
        val list = listOf(entity1, entity2)
        dao.insertAll(list)
        assert(dao.findAll() == list)
    }
}