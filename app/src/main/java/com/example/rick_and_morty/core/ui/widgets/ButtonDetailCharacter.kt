package com.example.rick_and_morty.core.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import com.example.rick_and_morty.core.ui.theme.font_size_16
import com.example.rick_and_morty.core.ui.theme.rounded12
import com.example.rick_and_morty.core.ui.theme.size_border_btn_1

@Composable
fun ButtonDetailCharacter(
    modifier: Modifier = Modifier,
    textButton: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonColors(
            contentColor = MaterialTheme.colorScheme.tertiary,
            containerColor = Color.Transparent,
            disabledContentColor = MaterialTheme.colorScheme.tertiary,
            disabledContainerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(size = rounded12),
        border = BorderStroke(
            width = size_border_btn_1,
            color = MaterialTheme.colorScheme.tertiary,
        )
    ) {
        Text(
            text = textButton,
            color = MaterialTheme.colorScheme.tertiary,
            textAlign = TextAlign.Center,
            fontSize = font_size_16
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewButtonDetailCharacter() {
    Rick_and_mortyTheme {
        ButtonDetailCharacter(
            textButton = "View all episodes",
            onClick = {}
        )
    }
}