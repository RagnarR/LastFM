package com.lastfm.app.ui.views

import androidx.navigation.NavController
import com.lastfm.app.data.api.dto.Album
import com.lastfm.app.data.api.dto.Artist
import com.lastfm.app.data.api.dto.Track

class LastFMNavigation(
    private val navController: NavController
) {

    fun navigateToAlbumDetailsScreen(album: Album) {
        navController.navigate(
            Screen.AlbumDetails.withArgs(
                album.name,
                album.artist
            )
        )
    }

    fun navigateToTopAlbumsScreen(artist: Artist) {
        navController.navigate(
            Screen.TopAlbums.withArgs(
                artist.name
            )
        )
    }
}