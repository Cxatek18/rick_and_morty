package com.example.rick_and_morty.characters_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.rick_and_morty.core.ui.components.characters.CharactersCard
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import org.junit.Rule
import org.junit.Test

class CharactersCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val characterID = 1
    private val imageCharacter = "https://test.image/url.jpg"
    private val nameCharacter = "Rick"
    private val statusCharacter = "Alive"

    private var clickedCount = 0

    private fun createContentCharactersCard() {
        composeTestRule.setContent {
            Rick_and_mortyTheme(darkTheme = false, dynamicColor = false) {
                CharactersCard(
                    characterID = characterID,
                    imageCharacter = imageCharacter,
                    nameCharacter = nameCharacter,
                    statusCharacter = statusCharacter,
                    onClickCharacter = {
                        clickedCount += 1
                    }
                )
            }
        }
    }

    @Test
    fun displayCharactersCardName() {
        createContentCharactersCard()
        composeTestRule.onNode(hasText(nameCharacter)).assertIsDisplayed()
    }

    @Test
    fun displayCharactersCardImage() {
        createContentCharactersCard()
        composeTestRule.onNode(hasContentDescription("image character")).assertIsDisplayed()
    }

    @Test
    fun onCLickCharacterTest() {
        createContentCharactersCard()

        composeTestRule.onNodeWithTag("CharactersCardColumn").performClick()
        assert(clickedCount == 1)
    }
}