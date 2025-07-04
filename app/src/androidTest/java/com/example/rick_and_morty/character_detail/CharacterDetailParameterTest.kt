package com.example.rick_and_morty.character_detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.rick_and_morty.core.ui.components.characters.CharacterDetailParameter
import org.junit.Rule
import org.junit.Test

class CharacterDetailParameterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testParamName = "Gender"
    private val testParamValue = "Male"

    private fun createContentCharacterDetailParameter() {
        composeTestRule.setContent {
            CharacterDetailParameter(
                nameParameter = testParamName,
                valueParameter = testParamValue
            )
        }
    }

    @Test
    fun displayNameParameter() {
        createContentCharacterDetailParameter()

        composeTestRule.onNodeWithTag("CharacterDetailParameterName")
            .assertIsDisplayed()
            .assertTextEquals(
                testParamName
            )
    }

    @Test
    fun displayValueParameter() {
        createContentCharacterDetailParameter()

        composeTestRule.onNodeWithTag("CharacterDetailParameterValue")
            .assertIsDisplayed()
            .assertTextEquals(
                testParamValue
            )
    }
}