package com.example.rick_and_morty.presentation.screens.episodes_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rick_and_morty.R
import com.example.rick_and_morty.core.ui.utils.IResourceService
import com.example.rick_and_morty.domain.interactor.episodes.IEpisodesListManagerInteractor
import com.example.rick_and_morty.domain.module.error_handler.ApiResult
import com.example.rick_and_morty.domain.module.error_handler.getStringSystemError
import com.example.rick_and_morty.presentation.screens.episodes_list.state.EpisodesListManagementState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesListManagementViewModel @Inject constructor(
    private val resolver: IResourceService,
    private val episodesListManagerInteractor: IEpisodesListManagerInteractor
) : ViewModel() {

    private val _state: MutableStateFlow<EpisodesListManagementState> =
        MutableStateFlow<EpisodesListManagementState>(
            EpisodesListManagementState.Loading
        )
    val state: StateFlow<EpisodesListManagementState> = _state.asStateFlow()

    private var searchNameJob: Job? = null
    private var searchCodeJob: Job? = null

    fun getListEpisodes(
        nameEpisode: String? = null,
        episodeCode: String? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_state.value::class.java != EpisodesListManagementState.Success::class.java) {
                _state.value = EpisodesListManagementState.Loading
                delay(timeMillis = 500)
            }
            episodesListManagerInteractor.getAllEpisodes(
                nameEpisode,
                episodeCode
            )
                .catch {
                    EpisodesListManagementState.Error(
                        errorText = resolver.getString(R.string.text_error_system),
                    )
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Error -> {
                            val systemErrorText = result.type.getStringSystemError(resolver)
                            if (systemErrorText != resolver.getString(R.string.text_error_in_server)) {
                                _state.value = EpisodesListManagementState.Error(
                                    errorText = systemErrorText,
                                    isRefreshing = false
                                )
                            }
                        }

                        is ApiResult.Success -> {
                            if (_state.value::class.java != EpisodesListManagementState.Success::class.java) {
                                _state.value = EpisodesListManagementState.Success(
                                    episodes = result.data.results,
                                    info = result.data.info,
                                )
                            } else {
                                when (_state.value) {
                                    is EpisodesListManagementState.Error, EpisodesListManagementState.Loading -> {}
                                    is EpisodesListManagementState.Success -> {
                                        val currentState =
                                            _state.value as EpisodesListManagementState.Success
                                        val updateState = currentState.copy(
                                            episodes = result.data.results,
                                            info = result.data.info,
                                            isRefreshing = false
                                        )
                                        _state.value = updateState
                                    }
                                }
                            }
                        }
                    }
                }
        }
    }

    fun onChangeTextNameSearchEpisode(name: String) {
        searchNameJob?.cancel()
        updateStateTextNameSearch(name)
        searchNameJob = viewModelScope.launch {
            delay(3000)
            executeSearchTextSearch()
        }
    }

    fun onChangeTextCodeSearchEpisode(code: String) {
        searchCodeJob?.cancel()
        updateStateTextCodeSearch(code)
        searchCodeJob = viewModelScope.launch {
            delay(3000)
            executeSearchTextSearch()
        }
    }

    fun pullToRefresh() {
        when (_state.value) {
            is EpisodesListManagementState.Error -> {
                val currentState = _state.value as EpisodesListManagementState.Error
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getListEpisodes(
                    nameEpisode = null,
                    episodeCode = null
                )
            }

            EpisodesListManagementState.Loading -> {}
            is EpisodesListManagementState.Success -> {
                val currentState = _state.value as EpisodesListManagementState.Success
                _state.value = currentState.copy(
                    isRefreshing = true
                )
                getListEpisodes(
                    nameEpisode = currentState.textNameSearchEpisode,
                    episodeCode = currentState.textCodeEpisodeSearch
                )
            }
        }
    }

    private fun updateStateTextCodeSearch(text: String) {
        val currentState = _state.value as EpisodesListManagementState.Success
        val updatedState = currentState.copy(
            textCodeEpisodeSearch = text.ifEmpty { null }
        )
        _state.value = updatedState
    }

    private fun updateStateTextNameSearch(text: String) {
        val currentState = _state.value as EpisodesListManagementState.Success
        val updatedState = currentState.copy(
            textNameSearchEpisode = text.ifEmpty { null }
        )
        _state.value = updatedState
    }

    private fun executeSearchTextSearch() {
        val currentState = _state.value as EpisodesListManagementState.Success
        getListEpisodes(
            nameEpisode = currentState.textNameSearchEpisode,
            episodeCode = currentState.textCodeEpisodeSearch
        )
    }
}