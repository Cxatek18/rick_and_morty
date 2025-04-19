package com.example.rick_and_morty.data.remote.mappers.characters

import com.example.rick_and_morty.data.remote.model.characters.CharacterItemInApi
import com.example.rick_and_morty.data.remote.model.characters.CharacterListInApi
import com.example.rick_and_morty.data.remote.model.characters.InfoCharacterListInApi
import com.example.rick_and_morty.domain.module.characters.CharacterInfo
import com.example.rick_and_morty.domain.module.characters.CharacterItem
import com.example.rick_and_morty.domain.module.characters.CharactersResult

fun InfoCharacterListInApi.toDomainCharacterInfo(): CharacterInfo {
    return CharacterInfo(
        count = this.count,
        pages = this.pages,
        next = this.next,
        prev = this.prev
    )
}

fun CharacterItemInApi.toDomainCharacterItem(): CharacterItem {
    return CharacterItem(
        id = this.id,
        name = this.name,
        status = this.status,
        image = this.image
    )
}

fun List<CharacterItemInApi>.toDomainListCharacterItem(): List<CharacterItem> {
    return this.map {
        it.toDomainCharacterItem()
    }
}

fun CharacterListInApi.toDomainCharactersResultCharacterListSuccess(): CharactersResult.CharacterListSuccess {
    return CharactersResult.CharacterListSuccess(
        info = info.toDomainCharacterInfo(),
        characterList = results.toDomainListCharacterItem()
    )
}