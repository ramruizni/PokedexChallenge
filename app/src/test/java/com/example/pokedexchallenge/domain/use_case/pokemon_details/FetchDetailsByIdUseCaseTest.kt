package com.example.pokedexchallenge.domain.use_case.pokemon_details

import app.cash.turbine.test
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.domain.repository.DetailsRepository
import com.example.pokedexchallenge.domain.repository.TestDetailsRepository
import com.example.pokedexchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchDetailsByIdUseCaseTest {

    private lateinit var fetchDetailsById: FetchDetailsByIdUseCase
    private lateinit var testDispatchers: TestDispatchers
    private lateinit var testDetailsRepository: DetailsRepository

    @Before
    fun setUp() {
        testDispatchers = TestDispatchers()
        testDetailsRepository = TestDetailsRepository()
        fetchDetailsById = FetchDetailsByIdUseCase(
            dispatchers = testDispatchers,
            detailsRepository = testDetailsRepository
        )
    }

    @Test
    fun `Emits loading event on start and on finish`() = runBlocking {
        fetchDetailsById(pokemonId = 1).test {
            assert(awaitItem() is Resource.Loading)
            assert(awaitItem() is Resource.Success)
            assert(awaitItem() is Resource.Loading)
            cancelAndConsumeRemainingEvents()
        }
    }
}