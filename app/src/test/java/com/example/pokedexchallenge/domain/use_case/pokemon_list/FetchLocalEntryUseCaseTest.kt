package com.example.pokedexchallenge.domain.use_case.pokemon_list

import app.cash.turbine.test
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.repository.EntryRepository
import com.example.pokedexchallenge.domain.repository.TestEntryRepository
import com.example.pokedexchallenge.testability.DispatcherProvider
import com.example.pokedexchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchLocalEntryUseCaseTest {
    private lateinit var fetchLocalEntry: FetchLocalEntryUseCase
    private lateinit var dispatchers: DispatcherProvider
    private lateinit var testEntryRepository: EntryRepository

    @Before
    fun setUp() {
        dispatchers = TestDispatchers()
        testEntryRepository = TestEntryRepository()
        fetchLocalEntry = FetchLocalEntryUseCase(
            dispatchers = dispatchers,
            entryRepository = testEntryRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        fetchLocalEntry(pokemonId = 1).test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}