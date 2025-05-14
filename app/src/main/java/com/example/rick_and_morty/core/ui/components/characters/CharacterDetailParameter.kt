package com.example.rick_and_morty.core.ui.components.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import com.example.rick_and_morty.core.ui.theme.font_size_12
import com.example.rick_and_morty.core.ui.theme.font_size_16

@Composable
fun CharacterDetailParameter(
    modifier: Modifier = Modifier,
    nameParameter: String,
    valueParameter: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = nameParameter,
            fontSize = font_size_12,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily.Serif,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = valueParameter,
            fontSize = font_size_16,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily.Serif,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCharacterDetailParameter() {
    Rick_and_mortyTheme {
        CharacterDetailParameter(
            nameParameter = "Gender",
            valueParameter = "Male"
        )
    }
}