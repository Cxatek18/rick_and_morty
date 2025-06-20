package com.example.rick_and_morty.core.ui.components.episode

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.theme.font_size_14
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.theme.padding_20
import com.example.rick_and_morty.core.ui.theme.padding_3
import com.example.rick_and_morty.core.ui.theme.padding_7
import com.example.rick_and_morty.core.ui.theme.size_height_character_image_in_episode
import com.example.rick_and_morty.domain.module.characters.CharacterItemModel

@Composable
fun CharacterInEpisodeItem(
    modifier: Modifier = Modifier,
    character: CharacterItemModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.tertiary,
                shape = RoundedCornerShape(size = padding_10)
            )
            .padding(all = padding_7),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier
                .size(size = size_height_character_image_in_episode)
                .clip(
                    shape = RoundedCornerShape(
                        size = padding_20
                    )
                ),
            model = character.image,
            contentDescription = stringResource(R.string.cd_image_character),
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(padding_3),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = character.name,
                fontSize = font_size_14,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.W500,
                fontFamily = FontFamily.Serif,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = character.status,
                fontSize = font_size_14,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.W500,
                fontFamily = FontFamily.Serif,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}