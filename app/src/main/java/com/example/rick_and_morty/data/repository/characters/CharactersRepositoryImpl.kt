package com.example.rick_and_morty.data.repository.characters


import com.example.rick_and_morty.data.remote.mappers.characters.toDomainCharactersResultCharacterListSuccess
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.domain.module.characters.CharactersResult
import com.example.rick_and_morty.domain.module.characters.ErrorType
import com.example.rick_and_morty.domain.repository.characters.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class CharactersRepositoryImpl(
    private val apiService: ApiService
) : CharactersRepository {

    override fun getListAllCharacters(): Flow<CharactersResult> = flow {
        try {
            val response = apiService.getListCharacter()
            if (response.isSuccessful) {
                emit(
                    value = response.body()?.toDomainCharactersResultCharacterListSuccess()
                        ?: CharactersResult.Error(ErrorType.NULL_TYPE)
                )
            } else {
                emit(
                    value = CharactersResult.Error(ErrorType.HTTP)
                )
            }
        } catch (error: IOException) {
            emit(value = CharactersResult.Error(ErrorType.NETWORK))
        } catch (error: Exception) {
            emit(value = CharactersResult.Error(ErrorType.SYSTEM))
        }
    }
}