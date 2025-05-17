package com.example.rick_and_morty.core.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.rick_and_morty.core.ui.theme.font_size_12
import com.example.rick_and_morty.core.ui.theme.padding_16
import com.example.rick_and_morty.core.ui.theme.padding_3
import com.example.rick_and_morty.core.ui.theme.rounded20
import com.example.rick_and_morty.core.ui.theme.size_border_btn_1

@Composable
fun FilterStringItem(
    modifier: Modifier = Modifier,
    filterTitle: String,
    isActiveFilter: Boolean,
    onClickToFilter: () -> Unit
) {
    val colorActive = if (isActiveFilter) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }

    val colorBackground = if (isActiveFilter) {
        MaterialTheme.colorScheme.tertiary
    } else {
        Color.Transparent
    }

    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(size = rounded20)
            )
            .background(
                color = colorBackground,
                shape = RoundedCornerShape(
                    size = rounded20
                )
            )
            .border(
                width = size_border_btn_1,
                color = colorActive,
                shape = RoundedCornerShape(
                    size = rounded20
                )
            )
            .clickable {
                onClickToFilter()
            }
            .padding(vertical = padding_3, horizontal = padding_16)
    ) {
        Text(
            text = filterTitle,
            fontSize = font_size_12,
            fontWeight = FontWeight.W300,
            color = colorActive
        )
    }
}