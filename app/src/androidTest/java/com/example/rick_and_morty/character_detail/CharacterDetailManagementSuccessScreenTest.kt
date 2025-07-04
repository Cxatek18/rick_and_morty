package com.example.rick_and_morty.character_detail

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.rick_and_morty.domain.module.character.CharacterDetailResultModel
import com.example.rick_and_morty.domain.module.character.CharacterLocationModel
import com.example.rick_and_morty.domain.module.character.CharacterOriginModel
import com.example.rick_and_morty.presentation.screens.character_detail.CharacterDetailManagementSuccessScreen
import org.junit.Rule
import org.junit.Test

class CharacterDetailManagementSuccessScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testCharacter = CharacterDetailResultModel(
        id = 7064,
        name = "Tamera Bennett",
        status = "Alive",
        species = "mandamus",
        type = "laudem",
        gender = "aeque",
        origin = CharacterOriginModel(
            name = "Mavis Hughes",
            url = "http://www.bing.com/search?q=vocent"
        ),
        location = CharacterLocationModel(
            name = "Armando Herrera",
            url = "https://www.google.com/#q=lorem"
        ),
        image = "https://test.image/url.jpg",
        episode = listOf(),
        url = "https://duckduckgo.com/?q=persius",
        created = "idque"
    )

    private fun createContentCharacterDetailScreen(
        onClickBtnViewAllEpisodes: () -> Unit = {}
    ) {
        composeTestRule.setContent {
            CharacterDetailManagementSuccessScreen(
                character = testCharacter,
                onClickBtnViewAllEpisodes = {
                    onClickBtnViewAllEpisodes()
                }
            )
        }
    }

    @Test
    fun displaysCharacterName() {
        createContentCharacterDetailScreen()

        composeTestRule.onNodeWithTag("CharacterDetailName")
            .assertIsDisplayed()
            .assertTextEquals(testCharacter.name)
    }

    @Test
    fun displaysCharacterStatus() {
        createContentCharacterDetailScreen()

        if (testCharacter.status != null) {
            composeTestRule.onNodeWithTag("CharacterDetailStatus")
                .assertIsDisplayed()
                .assertTextEquals(testCharacter.status!!)
        } else {
            composeTestRule.onNodeWithTag("CharacterDetailStatus")
                .assertIsDisplayed()
                .assertTextEquals("Опа null type")
        }
    }

    @Test
    fun displaysCharacterImage() {
        createContentCharacterDetailScreen()

        composeTestRule.onNodeWithTag("CharacterDetailImage")
            .assertIsDisplayed()

        composeTestRule.onNode(
            hasTestTag("CharacterDetailImage") and hasContentDescription(
                "image character"
            )
        ).assertExists()
    }

    @Test
    fun displaysCharacterBtnViewAllEpisodes() {
        var clicked = false

        createContentCharacterDetailScreen(
            onClickBtnViewAllEpisodes = {
                clicked = true
            }
        )

        composeTestRule.onNodeWithTag("CharacterDetailBtnViewAllEpisodes").performClick()
        assert(clicked)
    }

    @Test
    fun columnCharacterDetailIsScroll() {
        createContentCharacterDetailScreen()

        composeTestRule.onNodeWithTag("CharacterDetailColumnScrolled")
            .assert(hasScrollAction())
    }
}