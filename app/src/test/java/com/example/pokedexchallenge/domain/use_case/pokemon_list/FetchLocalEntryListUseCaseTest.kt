package com.example.pokedexchallenge.domain.use_case.pokemon_list

import app.cash.turbine.test
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.repository.EntryRepository
import com.example.pokedexchallenge.domain.repository.TestEntryRepository
import com.example.pokedexchallenge.testability.DispatcherProvider
import com.example.pokedexchallenge.testability.TestDispatcherRule
import com.example.pokedexchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FetchLocalEntryListUseCaseTest {
    private lateinit var dispatchers: DispatcherProvider
    private lateinit var fetchLocalEntryList: FetchLocalEntryListUseCase
    private lateinit var testEntryRepository: EntryRepository

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Before
    fun setUp() {
        dispatchers = TestDispatchers()
        testEntryRepository = TestEntryRepository()
        fetchLocalEntryList = FetchLocalEntryListUseCase(
            dispatchers = dispatchers,
            entryRepository = testEntryRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        fetchLocalEntryList(pokemonTypeList = emptyList()).test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}