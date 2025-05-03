package com.example.rick_and_morty.data.characters

import app.cash.turbine.test
import com.example.rick_and_morty.data.remote.mappers.characters.CharacterListManagementMappers
import com.example.rick_and_morty.data.remote.model.characters.CharacterItemInResponse
import com.example.rick_and_morty.data.remote.model.characters.CharacterListInResponse
import com.example.rick_and_morty.data.remote.model.characters.InfoCharacterListInResponse
import com.example.rick_and_morty.data.remote.network.ApiService
import com.example.rick_and_morty.data.repository.characters.CharactersRepositoryImpl
import com.example.rick_and_morty.domain.module.characters.CharacterInfoModel
import com.example.rick_and_morty.domain.module.characters.CharacterInfoStatus
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.characters.CharactersResultModel
import com.google.ar.core.Config
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class CharactersRepositoryImplTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    private val apiService: ApiService = mock()
    private val characterMappers: CharacterListManagementMappers = mock()
    private lateinit var repository: CharactersRepositoryImpl

    @Test
    fun `getListAllCharacters should emit mapped success result`() = runTest {
        // Arrange
        val apiResponse = CharacterListInResponse(
            info = InfoCharacterListInResponse(10, 2, "next", "prev"),
            results = listOf(CharacterItemInResponse(1, "Rick", "Alive", "image"))
        )

        val mappedResult = CharactersResultModel(
            info = CharacterInfoModel(10, 2, "next", "prev"),
            characterList = listOf(CharacterItemModel(1, "Rick", CharacterInfoStatus.Alive, "image"))
        )

        whenever(apiService.getListCharacter()).thenReturn(apiResponse)
        whenever(characterMappers.toDomainCharactersResultCharacterListSuccess(apiResponse))
            .thenReturn(mappedResult)

        repository = CharactersRepositoryImpl(apiService, characterMappers)

        // Act & Assert
        repository.getListAllCharacters().test {
            val result = awaitItem()

            assertEquals(mappedResult, result.getOrNull())
            verify(apiService).getListCharacter()
            verify(characterMappers).toDomainCharactersResultCharacterListSuccess(apiResponse)
            awaitComplete()
        }
    }

    @Test
    fun `getListAllCharacters should emit error on exception`() = runTest {
        // Arrange
        val error = IOException("Network error")
        whenever(apiService.getListCharacter()).thenThrow(error)

        repository = CharactersRepositoryImpl(apiService, characterMappers)

        // Act & Assert
        repository.getListAllCharacters().test {
            val result = awaitItem()

            assertTrue(result.isFailure)
            assertEquals(error, result.exceptionOrNull())
            awaitComplete()
        }
    }
}