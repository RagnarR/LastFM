package com.lastfm.app.model

import com.lastfm.app.data.api.dto.TopAlbumsResponse


sealed class TopAlbumsListState {
    object Loading : TopAlbumsListState()
    data class Loaded(
        val albums: TopAlbumsResponse
    ) : TopAlbumsListState()

    object GenericError : TopAlbumsListState()
    object NetworkError : TopAlbumsListState()
}
