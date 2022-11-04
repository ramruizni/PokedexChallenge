package com.example.pokedexchallenge.presentation.pokemon_list

import com.example.pokedexchallenge.domain.repository.EntryRepository
import com.example.pokedexchallenge.domain.repository.TestEntryRepository
import com.example.pokedexchallenge.domain.repository.TestTypesRepository
import com.example.pokedexchallenge.domain.repository.TypesRepository
import com.example.pokedexchallenge.domain.use_case.pokemon_list.*
import com.example.pokedexchallenge.testability.DispatcherProvider
import com.example.pokedexchallenge.testability.TestDispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PokemonListViewModelTest {
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var dispatchers: DispatcherProvider

    private lateinit var useCases: PokemonListUseCases
    private lateinit var typesRepository: TypesRepository
    private lateinit var entryRepository: EntryRepository

    @Before
    fun setUp() {
        dispatchers = TestDispatchers()
        typesRepository = TestTypesRepository()
        entryRepository = TestEntryRepository()
        useCases = PokemonListUseCases(
            FetchTypesListUseCase(dispatchers, typesRepository),
            FetchLocalEntryListUseCase(dispatchers, entryRepository),
            FetchRemoteEntryListUseCase(dispatchers, entryRepository),
            FetchLocalEntryUseCase(dispatchers, entryRepository),
            UpdateLocalEntryUseCase(dispatchers, entryRepository),
            FetchFavoritesEntryListUseCase(dispatchers, entryRepository)
        )

        viewModel = PokemonListViewModel(
            dispatchers = dispatchers,
            useCases = useCases,
        )
    }

    @Test
    fun `Fetch remote entry list, stores new data omg`() = runBlocking {
        println("AZAZA The list is ${viewModel.state.entries}")
        assert(viewModel.state.entries.isNotEmpty())
    }
}