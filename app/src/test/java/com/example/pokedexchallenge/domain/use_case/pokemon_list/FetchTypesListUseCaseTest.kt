package com.example.pokedexchallenge.domain.use_case.pokemon_list

import app.cash.turbine.test
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.repository.TestTypesRepository
import com.example.pokedexchallenge.domain.repository.TypesRepository
import com.example.pokedexchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchTypesListUseCaseTest {

    private lateinit var fetchTypesListUseCase: FetchTypesListUseCase
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var testTypesRepository: TypesRepository

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        testTypesRepository = TestTypesRepository()
        fetchTypesListUseCase = FetchTypesListUseCase(
            dispatchers = testDispatchers,
            typesRepository = testTypesRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        fetchTypesListUseCase().test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}