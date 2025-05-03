package com.example.rick_and_morty.presentation.screens.character_detail

import android.widget.Space
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.components.characters.CharacterDetailParameter
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import com.example.rick_and_morty.core.ui.theme.font_size_12
import com.example.rick_and_morty.core.ui.theme.font_size_26
import com.example.rick_and_morty.core.ui.theme.letter_spacing_1_2
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.theme.padding_16
import com.example.rick_and_morty.core.ui.theme.padding_20
import com.example.rick_and_morty.core.ui.theme.padding_3
import com.example.rick_and_morty.core.ui.theme.padding_36
import com.example.rick_and_morty.core.ui.theme.rounded20
import com.example.rick_and_morty.core.ui.theme.size_10
import com.example.rick_and_morty.core.ui.theme.size_25
import com.example.rick_and_morty.core.ui.theme.size_border_card_2
import com.example.rick_and_morty.core.ui.theme.size_height_character_image
import com.example.rick_and_morty.core.ui.widgets.ButtonDetailCharacter
import com.example.rick_and_morty.domain.module.character.CharacterDetailResultModel
import com.example.rick_and_morty.domain.module.character.CharacterLocationModel
import com.example.rick_and_morty.domain.module.character.CharacterOriginModel

@Composable
fun CharacterDetailManagementSuccessScreen(
    modifier: Modifier = Modifier,
    character: CharacterDetailResultModel,
    onClickBtnViewAllEpisodes: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .padding(
                all = padding_10
            ),
    ) {
        val statusCharacterColor = when (character.status) {
            stringResource(R.string.text_character_status_alive) -> {
                MaterialTheme.colorScheme.surfaceVariant
            }

            stringResource(R.string.text_character_status_dead) -> {
                MaterialTheme.colorScheme.onSurfaceVariant
            }

            else -> {
                MaterialTheme.colorScheme.secondary
            }
        }

        Box(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(size = rounded20),
                )
                .border(
                    width = size_border_card_2,
                    color = statusCharacterColor,
                    shape = RoundedCornerShape(size = rounded20)
                )
                .padding(vertical = padding_3, horizontal = padding_16)
        ) {
            Text(
                text = "Status: ${character.status}",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = font_size_12,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily.SansSerif
            )
        }

        Spacer(modifier = Modifier.height(height = size_10))

        Text(
            text = character.name,
            color = MaterialTheme.colorScheme.tertiary,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.W700,
            fontSize = font_size_26,
            letterSpacing = letter_spacing_1_2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(size_10))

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = size_height_character_image),
            model = character.image,
            contentDescription = stringResource(R.string.cd_image_character),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(size_25))

        CharacterDetailParameter(
            nameParameter = stringResource(R.string.text_character_parameter_location),
            valueParameter = character.location.name
        )

        Spacer(modifier = Modifier.height(padding_20))

        CharacterDetailParameter(
            nameParameter = stringResource(R.string.text_character_parameter_species),
            valueParameter = character.species
        )

        Spacer(modifier = Modifier.height(padding_20))

        CharacterDetailParameter(
            nameParameter = stringResource(R.string.text_character_parameter_gender),
            valueParameter = character.gender
        )

        Spacer(modifier = Modifier.height(padding_20))

        if (character.type.isNotEmpty()) {
            CharacterDetailParameter(
                nameParameter = stringResource(R.string.text_character_parameter_type),
                valueParameter = character.type
            )

            Spacer(modifier = Modifier.height(padding_20))
        }

        CharacterDetailParameter(
            nameParameter = stringResource(R.string.text_character_parameter_origin),
            valueParameter = character.origin.name
        )

        Spacer(modifier = Modifier.height(padding_20))

        CharacterDetailParameter(
            nameParameter = stringResource(R.string.text_character_parameter_episode_count),
            valueParameter = character.episode.count().toString()
        )

        Spacer(modifier = Modifier.height(height = padding_20))

        ButtonDetailCharacter(
            modifier = Modifier.padding(horizontal = padding_36),
            textButton = stringResource(R.string.text_btn_view_all_episodes),
            onClick = onClickBtnViewAllEpisodes
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCharacterDetailManagementSuccessScreen() {
    Rick_and_mortyTheme {
        CharacterDetailManagementSuccessScreen(
            character = CharacterDetailResultModel(
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
                image = "harum",
                episode = listOf(),
                url = "https://duckduckgo.com/?q=persius",
                created = "idque"
            ),
            onClickBtnViewAllEpisodes = {}
        )
    }
}