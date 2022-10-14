package com.example.pokedexchallenge.domain.use_case.pokemon_list

import app.cash.turbine.test
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.repository.EntryRepository
import com.example.pokedexchallenge.domain.repository.TestEntryRepository
import com.example.pokedexchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchLocalEntryUseCaseTest {

    private lateinit var fetchLocalEntryUseCase: FetchLocalEntryUseCase
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var testEntryRepository: EntryRepository

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        testEntryRepository = TestEntryRepository()
        fetchLocalEntryUseCase = FetchLocalEntryUseCase(
            dispatchers = testDispatchers,
            entryRepository = testEntryRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        fetchLocalEntryUseCase(pokemonId = 1).test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}