package com.example.rick_and_morty.core.ui.components.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import com.example.rick_and_morty.core.ui.theme.font_size_16
import com.example.rick_and_morty.core.ui.theme.line_height_20
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.theme.padding_7
import com.example.rick_and_morty.core.ui.theme.rounded20
import com.example.rick_and_morty.core.ui.theme.size_border_card_2
import com.example.rick_and_morty.core.ui.theme.size_circle_card_5
import com.example.rick_and_morty.core.ui.theme.size_height_card
import com.example.rick_and_morty.core.ui.theme.size_height_card_image
import com.example.rick_and_morty.core.ui.theme.size_width_card

@Composable
fun CharactersCard(
    modifier: Modifier = Modifier,
    characterID: Int,
    imageCharacter: String,
    nameCharacter: String,
    statusCharacter: String,
    onClickCharacter: (characterID: Int) -> Unit
) {
    Column(
        modifier = modifier
            .testTag("CharactersCardColumn")
            .size(width = size_width_card, height = size_height_card)
            .padding(vertical = padding_10, horizontal = padding_10)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(
                    size = rounded20
                )
            )
            .border(
                width = size_border_card_2,
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(
                    size = rounded20
                )
            )
            .clickable {
                onClickCharacter(characterID)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = rounded20,
                        topEnd = rounded20
                    )
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .testTag("CharactersCardImage")
                    .fillMaxWidth()
                    .height(height = size_height_card_image),
                model = imageCharacter,
                contentDescription = stringResource(R.string.cd_image_character),
                contentScale = ContentScale.Crop
            )

            val colorStatusCharacter = if (
                statusCharacter == stringResource(R.string.text_character_status_alive)
            ) {
                MaterialTheme.colorScheme.surfaceVariant
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant
            }

            if (statusCharacter != stringResource(R.string.text_character_status_unknown)) {
                Box(
                    modifier = Modifier
                        .padding(
                            top = padding_10,
                            start = padding_10
                        )
                        .size(size = size_circle_card_5)
                        .background(
                            color = colorStatusCharacter, shape = CircleShape
                        )
                )
            }
        }

        Text(
            modifier = Modifier
                .testTag("CharactersCardName")
                .padding(top = padding_10)
                .fillMaxWidth()
                .padding(horizontal = padding_7),
            text = nameCharacter,
            color = MaterialTheme.colorScheme.secondary,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W500,
            fontSize = font_size_16,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            lineHeight = line_height_20
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCharactersCard() {
    Rick_and_mortyTheme(darkTheme = false, dynamicColor = false) {
        CharactersCard(
            characterID = 1,
            imageCharacter = "facilis",
            nameCharacter = "Damian Sherman",
            statusCharacter = "Dead",
            onClickCharacter = {}
        )
    }
}