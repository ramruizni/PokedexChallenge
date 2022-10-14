package com.example.pokedexchallenge.di

import com.example.pokedexchallenge.domain.repository.DetailsRepository
import com.example.pokedexchallenge.domain.repository.EntryRepository
import com.example.pokedexchallenge.domain.repository.TypesRepository
import com.example.pokedexchallenge.domain.use_case.login.LoginUseCases
import com.example.pokedexchallenge.domain.use_case.login.ValidateEmailUseCase
import com.example.pokedexchallenge.domain.use_case.login.ValidatePasswordUseCase
import com.example.pokedexchallenge.domain.use_case.login.ValidateTermsUseCase
import com.example.pokedexchallenge.domain.use_case.pokemon_details.FetchDetailsByIdUseCase
import com.example.pokedexchallenge.domain.use_case.pokemon_details.PokemonDetailsUseCases
import com.example.pokedexchallenge.domain.use_case.pokemon_list.*
import com.example.pokedexchallenge.testability.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideLoginUseCases(): LoginUseCases {
        return LoginUseCases(
            ValidateEmailUseCase(),
            ValidatePasswordUseCase(),
            ValidateTermsUseCase()
        )
    }

    @Provides
    @Singleton
    fun providePokemonListUseCases(
        dispatchers: DispatcherProvider,
        typesRepository: TypesRepository,
        entryRepository: EntryRepository,
    ): PokemonListUseCases {
        return PokemonListUseCases(
            FetchTypesListUseCase(dispatchers, typesRepository),
            FetchLocalEntryListUseCase(dispatchers, entryRepository),
            FetchRemoteEntryListUseCase(dispatchers, entryRepository),
            FetchLocalEntryUseCase(dispatchers, entryRepository),
            UpdateLocalEntryUseCase(dispatchers, entryRepository),
            FetchFavoritesEntryListUseCase(dispatchers, entryRepository)
        )
    }

    @Provides
    @Singleton
    fun providePokemonDetailsUseCases(
        dispatchers: DispatcherProvider,
        detailsRepository: DetailsRepository,
        entryRepository: EntryRepository,
    ): PokemonDetailsUseCases {
        return PokemonDetailsUseCases(
            FetchDetailsByIdUseCase(dispatchers, detailsRepository),
            FetchLocalEntryUseCase(dispatchers, entryRepository),
            UpdateLocalEntryUseCase(dispatchers, entryRepository)
        )
    }
}