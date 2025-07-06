package com.example.rick_and_morty.characters_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel
import com.example.rick_and_morty.presentation.screens.characters_list.CharactersListManagementSuccessScreen
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class CharactersListManagementSuccessScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testCharacters = listOf(
        CharacterItemModel(id = 1, name = "Rick", image = "url1", status = "Alive"),
        CharacterItemModel(id = 2, name = "Morty", image = "url2", status = "Alive")
    )

    private val testPagingData = PagingData.from(testCharacters)
    private val testFlow = flowOf(testPagingData)

    private var clickedId: Int? = null

    private fun createContentCharactersListScreen() {
        composeTestRule.setContent {
            CharactersListManagementSuccessScreen(
                characters = testFlow,
                onClickCharacter = {
                    clickedId = it
                }
            )
        }
    }

    @Test
    fun displayedCardsInScreen() {
        createContentCharactersListScreen()

        composeTestRule.onNodeWithTag("CharactersCard_1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("CharactersCard_2").assertIsDisplayed()

        composeTestRule.onNodeWithText("Rick").assertIsDisplayed()
        composeTestRule.onNodeWithText("Morty").assertIsDisplayed()

        composeTestRule.onNodeWithTag("CharactersCard_1").performClick()
        assert(clickedId == 1)
    }
}