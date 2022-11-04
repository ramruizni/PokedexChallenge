package com.example.pokedexchallenge.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pokedexchallenge.data.local.PokemonDB
import com.example.pokedexchallenge.data.mapper.toEntity
import com.example.pokedexchallenge.domain.model.Details
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@SmallTest
class DetailsDaoTest {
    private lateinit var db: PokemonDB
    private lateinit var dao: DetailsDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PokemonDB::class.java
        ).allowMainThreadQueries().build()
        dao = db.getDetailsDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    private val entity1 = Details(
        pokemonId = 1,
        hp = 10,
        attack = 10,
        defense = 10,
        spAtk = 10,
        spDef = 10,
        speed = 10
    ).toEntity()

    private val entity2 = entity1.copy(pokemonId = 2)

    @Test
    fun findsEntitiesByPokemonIdCorrectly() = runBlocking {
        dao.insert(entity1)
        dao.insert(entity2)

        assert(dao.findByPokemonId(1) == entity1)
        assert(dao.findByPokemonId(2) == entity2)
        assert(dao.findByPokemonId(3) == null)
    }
}