package com.example.rick_and_morty.core.ui.components.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.theme.font_size_12
import com.example.rick_and_morty.core.ui.theme.font_size_14
import com.example.rick_and_morty.core.ui.theme.font_size_16
import com.example.rick_and_morty.core.ui.theme.line_height_20
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.theme.padding_3
import com.example.rick_and_morty.core.ui.theme.padding_7
import com.example.rick_and_morty.core.ui.theme.rounded12
import com.example.rick_and_morty.core.ui.theme.size_width_card

@Composable
fun EpisodeCard(
    modifier: Modifier = Modifier,
    episodeName: String,
    episodeCode: String,
    episodeRelease: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = padding_7,
                horizontal = padding_10
            )
            .background(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = RoundedCornerShape(
                    size = rounded12
                )
            )
            .padding(
                vertical = padding_7,
                horizontal = padding_10
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(padding_3),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.text_episode),
                color = MaterialTheme.colorScheme.tertiary,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W500,
                fontSize = font_size_12,
                lineHeight = line_height_20
            )

            Text(
                text = episodeCode,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W400,
                fontSize = font_size_14,
                lineHeight = line_height_20
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(padding_3),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier
                    .width(width = size_width_card)
                    .align(Alignment.End),
                text = episodeName,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W400,
                fontSize = font_size_16,
                lineHeight = line_height_20,
                textAlign = TextAlign.End
            )

            Text(
                text = episodeRelease,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W400,
                fontSize = font_size_14,
                lineHeight = line_height_20
            )
        }
    }
}