package com.lastfm.app.ui.views.detailsscreen

import android.util.Log
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.lastfm.app.model.*
import com.lastfm.app.ui.views.LastFMNavigation
import com.lastfm.app.ui.views.listscreen.DisplayTopAlbums
import com.lastfm.app.ui.views.reusable_views.LastFMStandardProgressBar
import com.lastfm.app.viewmodels.LastFMAlbumDetailsViewModel
import com.lastfm.app.viewmodels.LastFMArtistTopAlbumsViewModel

private const val GENERIC_ERROR_MESSAGE = "Something went wrong"

@Composable
fun AlbumDetailsWithViewModel(
    lastFMAlbumDetailsViewModel: LastFMAlbumDetailsViewModel,
    album: String,
    artist: String
) {
    lastFMAlbumDetailsViewModel.loadAlbumDetails(albumName = album, artistName = artist)
    val currentState: State<AlbumDetailsState> =
        lastFMAlbumDetailsViewModel.albumDetailsViewState.collectAsState()
    when (val result = currentState.value) {
        is AlbumDetailsState.Loaded -> AlbumDetails(albumDetailsResponse = result.albumDetails)
        is AlbumDetailsState.Loading -> LastFMStandardProgressBar()
        else -> Text(
            text = GENERIC_ERROR_MESSAGE,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onError
        )
    }
}

@Composable
fun ArtistTopAlbumsWithViewModel(
    lastFMArtistTopAlbumsViewModel: LastFMArtistTopAlbumsViewModel,
    lastFMNavigation: LastFMNavigation,
    artist: String
) {
    lastFMArtistTopAlbumsViewModel.loadArtistTopAlbums(artistName = artist)
    val currentState: State<TopAlbumsListState> =
        lastFMArtistTopAlbumsViewModel.topAlbumsViewState.collectAsState()
    when (val result = currentState.value) {
        is TopAlbumsListState.Loaded -> DisplayTopAlbums(
            albumsResponse = result.albums,
            lastFMNavigation = lastFMNavigation
        )
        is TopAlbumsListState.Loading -> LastFMStandardProgressBar()
        else -> {
            Text(
            text = GENERIC_ERROR_MESSAGE,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onError
        ) }
    }
}