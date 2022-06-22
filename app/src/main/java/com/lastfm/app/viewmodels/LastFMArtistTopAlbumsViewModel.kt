package com.lastfm.app.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lastfm.app.domain.AlbumDetailsUseCase
import com.lastfm.app.domain.TopAlbumsUseCase
import com.lastfm.app.domain.UseCaseResult
import com.lastfm.app.model.AlbumDetailsState
import com.lastfm.app.model.AlbumsListState
import com.lastfm.app.model.TopAlbumsListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LastFMArtistTopAlbumsViewModel @Inject constructor(
    private val topAlbumsUseCase: TopAlbumsUseCase
) : ViewModel() {
    private val _topAlbumsViewState: MutableStateFlow<TopAlbumsListState> =
        MutableStateFlow(TopAlbumsListState.Loading)
    val topAlbumsViewState: StateFlow<TopAlbumsListState> = _topAlbumsViewState

    fun loadArtistTopAlbums(artistName: String) {
        viewModelScope.launch {
            _topAlbumsViewState.value =
                when (val result = topAlbumsUseCase.execute(artistName = artistName)) {
                    is UseCaseResult.SuccessResult -> {
                        TopAlbumsListState.Loaded(result.value)
                    }
                    is UseCaseResult.NetworkErrorResult -> TopAlbumsListState.NetworkError
                    is UseCaseResult.GenericErrorResult -> TopAlbumsListState.GenericError
                }
        }
    }
}