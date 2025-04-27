package com.example.rick_and_morty.data.remote.mappers.characters

import com.example.rick_and_morty.data.remote.model.characters.CharacterItemInResponse
import com.example.rick_and_morty.data.remote.model.characters.CharacterListInResponse
import com.example.rick_and_morty.data.remote.model.characters.InfoCharacterListInResponse
import com.example.rick_and_morty.domain.module.characters.CharacterInfoModel
import com.example.rick_and_morty.domain.module.characters.CharacterInfoStatus
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.domain.module.characters.CharactersResultModel
import javax.inject.Inject

class CharacterListManagementMappers @Inject constructor() {

    fun toDomainCharacterInfoModel(
        infoCharacterListInResponse: InfoCharacterListInResponse?
    ): CharacterInfoModel {
        return CharacterInfoModel(
            count = infoCharacterListInResponse?.count ?: 0,
            pages = infoCharacterListInResponse?.pages ?: 0,
            next = infoCharacterListInResponse?.next ?: "",
            prev = infoCharacterListInResponse?.prev ?: ""
        )
    }

    fun toDomainCharacterItemModel(
        characterItemInResponse: CharacterItemInResponse
    ): CharacterItemModel {
        return CharacterItemModel(
            id = characterItemInResponse?.id ?: -1,
            name = characterItemInResponse.name ?: "",
            status = CharacterInfoStatus
                .entries.find {
                    it.statusName == characterItemInResponse?.status
                } ?: CharacterInfoStatus.Unknown,
            image = characterItemInResponse?.image ?: ""
        )
    }

    fun toDomainListCharacterItem(
        listCharacterItemInResponse: List<CharacterItemInResponse>
    ): List<CharacterItemModel> {
        return listCharacterItemInResponse.map {
            toDomainCharacterItemModel(it)
        }
    }

    fun toDomainCharactersResultCharacterListSuccess(
        characterListInResponse: CharacterListInResponse?
    ): CharactersResultModel {
        return CharactersResultModel(
            info = toDomainCharacterInfoModel(characterListInResponse?.info),
            characterList = toDomainListCharacterItem(
                characterListInResponse?.results ?: emptyList()
            )
        )
    }
}