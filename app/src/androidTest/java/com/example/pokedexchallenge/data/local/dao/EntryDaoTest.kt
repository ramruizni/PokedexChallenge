package com.example.pokedexchallenge.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.pokedexchallenge.data.local.PokemonDB
import com.example.pokedexchallenge.data.mapper.toEntity
import com.example.pokedexchallenge.domain.model.Entry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class EntryDaoTest {
    private lateinit var db: PokemonDB
    private lateinit var dao: EntryDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PokemonDB::class.java
        ).allowMainThreadQueries().build()
        dao = db.getEntryDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    private val entity1 = Entry(
        id = 1,
        name = "Bulbasaur",
        url = "fake_url",
        typeName1 = "Grass",
        typeName2 = "Poison",
        isFavorite = false
    ).toEntity()
    private val entity2 = Entry(
        id = 2,
        name = "Ivysaur",
        url = "fake_url",
        typeName1 = "Grass",
        typeName2 = "Poison",
        isFavorite = true
    ).toEntity()

    @Test
    fun insertsMultipleEntitiesCorrectly() = runTest {
        val list = listOf(entity1, entity2)
        dao.insertAll(list)
        assert(dao.findAll() == list)
    }

    @Test
    fun findsEntitiesByPokemonIdCorrectly() = runTest {
        dao.insertAll(listOf(entity1, entity2))
        assert(dao.find(pokemonId = 2) == entity2)
    }

    @Test
    fun findsAllFavoriteEntitiesCorrectly() = runTest {
        dao.insertAll(listOf(entity1, entity2))
        assert(dao.findAllFavorites() == listOf(entity2))
    }

    @Test
    fun updatesEntitiesCorrectly() = runTest {
        dao.insertAll(listOf(entity1, entity2))

        val entity1Updated = entity1.copy(
            name = "Venusaur",
            isFavorite = false
        )
        val entity2Updated = entity2.copy(
            name = "Kadabra",
            typeName2 = "Psychic"
        )

        dao.update(entity1Updated)
        dao.update(entity2Updated)

        assert(dao.findAll() == listOf(entity1Updated, entity2Updated))
    }
}