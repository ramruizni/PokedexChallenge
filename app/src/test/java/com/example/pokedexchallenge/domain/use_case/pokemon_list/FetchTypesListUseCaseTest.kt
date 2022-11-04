package com.example.pokedexchallenge.domain.use_case.pokemon_list

import app.cash.turbine.test
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.repository.TestTypesRepository
import com.example.pokedexchallenge.domain.repository.TypesRepository
import com.example.pokedexchallenge.testability.DispatcherProvider
import com.example.pokedexchallenge.testability.TestDispatcherRule
import com.example.pokedexchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FetchTypesListUseCaseTest {
    private lateinit var dispatchers: DispatcherProvider
    private lateinit var fetchTypesList: FetchTypesListUseCase
    private lateinit var testTypesRepository: TypesRepository

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Before
    fun setUp() {
        dispatchers = TestDispatchers()
        testTypesRepository = TestTypesRepository()
        fetchTypesList = FetchTypesListUseCase(
            dispatchers = dispatchers,
            typesRepository = testTypesRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        fetchTypesList().test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}