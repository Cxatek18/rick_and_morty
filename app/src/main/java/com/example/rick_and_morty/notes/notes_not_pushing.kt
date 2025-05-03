package com.example.rick_and_morty.notes

// Characters List Models in Api

//data class CharacterListInResponse(
//    val info: InfoCharacterListInResponse,
//    val results: List<CharacterItemInResponse>
//)
//
//data class InfoCharacterListInResponse(
//    val count: Int,
//    val pages: Int,
//    val next: String?,
//    val prev: String?
//)
//
//data class LocationCharacterInResponse(
//    val name: String,
//    val url: String
//)
//
//data class OriginCharacterInResponse(
//    val name: String,
//    val url: String
//)
//
//data class CharacterItemInResponse(
//    val id: Int,
//    val name: String,
//    val status: String,
//    val species: String,
//    val type: String,
//    val gender: String,
//    @SerializedName("origin")
//    val originCharacter: OriginCharacterInResponse,
//    @SerializedName("location")
//    val locationCharacter: LocationCharacterInResponse,
//    val image: String,
//    val episode: List<String>,
//    val url: String,
//    val created: String
//)

//class CharacterListManagementMappers @Inject constructor() {
//
//    fun toDomainCharacterInfoModel(
//        infoCharacterListInResponse: InfoCharacterListInResponse?
//    ): CharacterInfoModel {
//        return CharacterInfoModel(
//            count = infoCharacterListInResponse?.count ?: 0,
//            pages = infoCharacterListInResponse?.pages ?: 0,
//            next = infoCharacterListInResponse?.next ?: "",
//            prev = infoCharacterListInResponse?.prev ?: ""
//        )
//    }
//
//    fun toDomainCharacterItemModel(
//        characterItemInResponse: CharacterItemInResponse
//    ): CharacterItemModel {
//        return CharacterItemModel(
//            id = characterItemInResponse?.id ?: -1,
//            name = characterItemInResponse.name ?: "",
//            status = CharacterInfoStatus
//                .entries.find {
//                    it.statusName == characterItemInResponse?.status
//                } ?: CharacterInfoStatus.Unknown,
//            image = characterItemInResponse?.image ?: ""
//        )
//    }
//
//    fun toDomainListCharacterItem(
//        listCharacterItemInResponse: List<CharacterItemInResponse>
//    ): List<CharacterItemModel> {
//        return listCharacterItemInResponse.map {
//            toDomainCharacterItemModel(it)
//        }
//    }
//
//    fun toDomainCharactersResultCharacterListSuccess(
//        characterListInResponse: CharacterListInResponse?
//    ): CharactersResultModel {
//        return CharactersResultModel(
//            info = toDomainCharacterInfoModel(characterListInResponse?.info),
//            characterList = toDomainListCharacterItem(
//                characterListInResponse?.results ?: emptyList()
//            )
//        )
//    }
//}

// Characters List Models in Api