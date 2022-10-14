package com.example.pokedexchallenge.domain.use_case.pokemon_list

import app.cash.turbine.test
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.repository.EntryRepository
import com.example.pokedexchallenge.domain.repository.TestEntryRepository
import com.example.pokedexchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchRemoteEntryListUseCaseTest {

    private lateinit var fetchRemoteEntryListUseCase: FetchRemoteEntryListUseCase
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var testEntryRepository: EntryRepository

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        testEntryRepository = TestEntryRepository()
        fetchRemoteEntryListUseCase = FetchRemoteEntryListUseCase(
            dispatchers = testDispatchers,
            entryRepository = testEntryRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        fetchRemoteEntryListUseCase(
            offset = 0,
            pokemonTypeList = emptyList()
        ).test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}