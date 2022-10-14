package com.example.pokedexchallenge.domain.use_case.pokemon_details

import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.commons.UiText
import com.example.pokedexchallenge.domain.model.Details
import com.example.pokedexchallenge.domain.repository.DetailsRepository
import com.example.pokedexchallenge.testability.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class FetchDetailsByIdUseCase @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val detailsRepository: DetailsRepository,
) {

    suspend operator fun invoke(pokemonId: Int): Flow<Resource<Details>> {
        return flow {
            emit(Resource.Loading(true))

            val result = detailsRepository.fetchDetailsById(pokemonId)

            when (result) {
                is Resource.Success -> {
                    emit(result)
                }
                is Resource.Error -> {
                    emit(Resource.Error(UiText.StringResource(R.string.error_unknown)))
                }
                else -> Unit
            }

            emit(Resource.Loading(false))
        }.catch {
            emit(Resource.Error(UiText.StringResource(R.string.error_unknown)))
        }.flowOn(dispatchers.io)
    }

}