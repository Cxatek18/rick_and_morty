package com.example.rick_and_morty.presentation.screens.characters_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.theme.Rick_and_mortyTheme
import com.example.rick_and_morty.domain.module.error_handler.ErrorType
import com.example.rick_and_morty.presentation.screens.characters_list.state.CharactersListManagementState
import com.example.rick_and_morty.presentation.screens.characters_list.view_model.CharactersListManagementViewModel

@Composable
fun CharactersListManagement(
    modifier: Modifier = Modifier,
    state: CharactersListManagementState,
    viewModel: CharactersListManagementViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        when (state) {
            is CharactersListManagementState.Error -> {
                val errorText = when (state.errorType) {
                    ErrorType.NETWORK -> stringResource(R.string.text_error_network)
                    ErrorType.HTTP -> stringResource(R.string.text_error_http)
                    ErrorType.SYSTEM -> stringResource(R.string.text_error_system)
                    ErrorType.NULL_TYPE -> stringResource(R.string.text_error_null_type)
                    ErrorType.UNKNOWN -> {
                        String.format(
                            stringResource(R.string.text_error_unknown),
                            state.errorType.code
                        )
                    }

                    else -> {
                        String.format(
                            stringResource(R.string.text_error_unknown),
                            state.errorType.code
                        )
                    }
                }
                CharactersListManagementErrorScreen(
                    errorText = errorText
                )
            }

            CharactersListManagementState.Loading -> {
                CharactersListManagementLoadingScreen()
            }

            is CharactersListManagementState.Success -> {
                CharactersListManagementSuccessScreen(
                    characters = state.characters
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewCharactersListScreen() {
    Rick_and_mortyTheme {

    }
}