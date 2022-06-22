package com.lastfm.app.ui.views.listscreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.lastfm.app.data.api.dto.*
import com.lastfm.app.ui.views.LastFMNavigation
import com.lastfm.app.ui.views.reusable_views.TextName
import com.lastfm.app.ui.views.reusable_views.SmallSpacer
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayAlbums(
    albumsResponse: AlbumsResponse,
    lastFMNavigation: LastFMNavigation
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
            albumsResponse.results.albumMatches?.let {
                items(it.album) { album ->
                    AlbumCard(album = album, lastFMNavigation = lastFMNavigation)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayTopAlbums(
    albumsResponse: TopAlbumsResponse,
    lastFMNavigation: LastFMNavigation
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
            items(albumsResponse.results.album) { album ->
                AlbumCard(album = album.toAlbum(), lastFMNavigation = lastFMNavigation)
            }
        }
    }
}

@Composable
fun AlbumCard(album: Album, lastFMNavigation: LastFMNavigation) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .widthIn(100.dp)
            .heightIn(200.dp)
            .clickable {
                lastFMNavigation.navigateToAlbumDetailsScreen(album = album)
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(12.dp)
                .border(width = 1.dp, color = Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DisplayImages(url = album.image[2].text)
            SmallSpacer()
            TextName(name = album.name)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayArtists(
    artistsResponse: ArtistsResponse,
    lastFMNavigation: LastFMNavigation
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        LazyVerticalGrid(cells = GridCells.Adaptive(minSize = 128.dp)) {
            artistsResponse.results.artistMatches?.let {
                items(it.artist) { artist ->
                    ArtistCard(artist = artist, lastFMNavigation = lastFMNavigation)
                }
            }
        }
    }
}

@Composable
fun ArtistCard(artist: Artist, lastFMNavigation: LastFMNavigation) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .widthIn(125.dp)
            .heightIn(250.dp)
            .clickable {
                lastFMNavigation.navigateToTopAlbumsScreen(artist = artist)
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(12.dp)
                .border(width = 1.dp, color = Color.Gray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DisplayImages(url = artist.image?.get(2)?.text ?: "")
            SmallSpacer()
            TextName(name = artist.name)
        }
    }
}

@Composable
fun DisplayImages(url: String) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        modifier = Modifier.size(150.dp)
    )
}