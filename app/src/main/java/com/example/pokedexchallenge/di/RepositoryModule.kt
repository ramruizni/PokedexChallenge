package com.example.pokedexchallenge.di

import com.example.pokedexchallenge.data.repository.DetailsRepositoryImpl
import com.example.pokedexchallenge.data.repository.EntryRepositoryImpl
import com.example.pokedexchallenge.data.repository.TypesRepositoryImpl
import com.example.pokedexchallenge.domain.repository.DetailsRepository
import com.example.pokedexchallenge.domain.repository.EntryRepository
import com.example.pokedexchallenge.domain.repository.TypesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindTypesRepository(
        typesRepositoryImpl: TypesRepositoryImpl
    ): TypesRepository

    @Binds
    @Singleton
    abstract fun bindEntryRepository(
        entryRepositoryImpl: EntryRepositoryImpl
    ): EntryRepository

    @Binds
    @Singleton
    abstract fun bindDetailsRepository(
        detailsRepositoryImpl: DetailsRepositoryImpl
    ): DetailsRepository
}