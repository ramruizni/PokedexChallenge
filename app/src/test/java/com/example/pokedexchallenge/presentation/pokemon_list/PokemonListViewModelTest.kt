package com.example.pokedexchallenge.presentation.pokemon_list

import com.example.pokedexchallenge.domain.repository.EntryRepository
import com.example.pokedexchallenge.domain.repository.TestEntryRepository
import com.example.pokedexchallenge.domain.repository.TestTypesRepository
import com.example.pokedexchallenge.domain.repository.TypesRepository
import com.example.pokedexchallenge.domain.use_case.pokemon_list.*
import com.example.pokedexchallenge.testability.DispatcherProvider
import com.example.pokedexchallenge.testability.TestDispatchers
import com.example.pokedexchallenge.testability.rule.StandardDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonListViewModelTest {
    private lateinit var viewModel: PokemonListViewModel
    private lateinit var dispatchers: DispatcherProvider

    private lateinit var useCases: PokemonListUseCases
    private lateinit var typesRepository: TypesRepository
    private lateinit var entryRepository: EntryRepository

    @get:Rule
    val dispatcherRule = StandardDispatcherRule()

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
            useCases = useCases,
        )
    }

    @Test
    fun `Initializing the ViewModel, retrieves Types and Entries from Repository`() = runBlocking {
        assert(viewModel.state.types.isEmpty())
        assert(viewModel.state.entries.isEmpty())

        // wait for ViewModel's init block to complete
        dispatcherRule.dispatcher.scheduler.advanceUntilIdle()

        val repoTypes = typesRepository.fetchTypesList().data!!
        val repoEntries = entryRepository.fetchLocalEntryList(repoTypes).data
        assert(viewModel.state.types == repoTypes)
        assert(viewModel.state.entries == repoEntries)
    }
}