package com.example.rick_and_morty.core.ui.components.locations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.rick_and_morty.core.ui.theme.font_size_14
import com.example.rick_and_morty.core.ui.theme.font_size_16
import com.example.rick_and_morty.core.ui.theme.line_height_20
import com.example.rick_and_morty.core.ui.theme.padding_10
import com.example.rick_and_morty.core.ui.theme.padding_7
import com.example.rick_and_morty.core.ui.theme.rounded12
import com.example.rick_and_morty.core.ui.theme.size_width_card

@Composable
fun LocationsCard(
    modifier: Modifier = Modifier,
    locationId: Int,
    locationName: String,
    locationType: String,
    locationDimension: String,
    onClickNavigateToLocationsDetail: (Int) -> Unit
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
            )
            .clickable {
                onClickNavigateToLocationsDetail(locationId)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = padding_7)
        ) {
            Text(
                modifier = Modifier
                    .width(width = size_width_card),
                text = locationName,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W400,
                fontSize = font_size_16,
                lineHeight = line_height_20
            )

            Text(
                text = locationType,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W400,
                fontSize = font_size_16,
                lineHeight = line_height_20
            )
        }

        if (locationDimension != stringResource(R.string.text_character_status_unknown)) {
            Text(
                text = locationDimension,
                color = MaterialTheme.colorScheme.secondary,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.W400,
                fontSize = font_size_14,
                lineHeight = line_height_20,
                textAlign = TextAlign.End
            )
        }
    }
}