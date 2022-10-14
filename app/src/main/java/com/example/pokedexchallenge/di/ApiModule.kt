package com.example.pokedexchallenge.di


import com.example.pokedexchallenge.commons.Constants
import com.example.pokedexchallenge.data.remote.api.DetailsApi
import com.example.pokedexchallenge.data.remote.api.EntryApi
import com.example.pokedexchallenge.data.remote.api.TypesApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    @Singleton
    fun provideEntryApi(retrofit: Retrofit): EntryApi =
        retrofit.create(EntryApi::class.java)

    @Provides
    @Singleton
    fun provideTypesApi(retrofit: Retrofit): TypesApi =
        retrofit.create(TypesApi::class.java)

    @Provides
    @Singleton
    fun provideDetailsApi(retrofit: Retrofit): DetailsApi =
        retrofit.create(DetailsApi::class.java)
}