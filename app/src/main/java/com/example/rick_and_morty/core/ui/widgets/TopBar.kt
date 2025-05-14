package com.example.rick_and_morty.core.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import com.example.rick_and_morty.core.ui.theme.padding_7

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    nameTopBarState: String,
    isVisibleBackIcon: Boolean,
    onCLickBack: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets(0.dp)),
        title = {
            Text(
                text = nameTopBarState,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 24.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.W700,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = MaterialTheme.colorScheme.secondary,
            actionIconContentColor = MaterialTheme.colorScheme.secondary,
        ),
        expandedHeight = 35.dp,
        navigationIcon = {
            if (isVisibleBackIcon) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            onCLickBack()
                        },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.text_content_desc_back_icon),
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )
}

@Preview
@Composable
private fun PreviewTopBar() {
    Rick_and_mortyTheme {
        TopBar(
            nameTopBarState = "asdasdasd",
            isVisibleBackIcon = true,
            onCLickBack = {}
        )
    }
}