package com.example.pokedexchallenge.domain.use_case.pokemon_list

import com.example.pokedexchallenge.R
import com.example.pokedexchallenge.commons.Resource
import com.example.pokedexchallenge.commons.UiText
import com.example.pokedexchallenge.domain.model.Types
import com.example.pokedexchallenge.domain.repository.TypesRepository
import com.example.pokedexchallenge.testability.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class FetchTypesListUseCase @Inject constructor(
    private var dispatchers: DispatcherProvider,
    private val typesRepository: TypesRepository,
) {

    suspend operator fun invoke(): Flow<Resource<List<Types>>> {
        return flow {
            emit(Resource.Loading(true))

            val result = typesRepository.fetchTypesList()

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