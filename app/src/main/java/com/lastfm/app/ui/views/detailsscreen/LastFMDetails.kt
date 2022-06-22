package com.lastfm.app.ui.views.detailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.lastfm.app.data.api.dto.AlbumDetailsResponse
import com.lastfm.app.data.api.dto.ArtistDetailsResponse
import com.lastfm.app.ui.views.reusable_views.*

@Composable
fun AlbumDetails(albumDetailsResponse: AlbumDetailsResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        PageHeader(heading = albumDetailsResponse.album.name)
        MediumSpacer()
        DisplayLargeImage(url = albumDetailsResponse.album.image[3].text)
        LargeSpacer()
        ArtistName(artistName = albumDetailsResponse.album.artist)
        MediumSpacer()
        Listener(listener = albumDetailsResponse.album.listeners)
        MediumSpacer()
        PlayCount(playCount = albumDetailsResponse.album.playCount)
        MediumSpacer()
        albumDetailsResponse.album.albumTracks?.tracks?.let {
            it.forEachIndexed { index, item ->
                TrackName(trackName = item.name, index = index + 1)
            }
        }
        MediumSpacer()
        albumDetailsResponse.album.wiki?.let {
            WikiOrBio(wikiOrBio = it.content)
        }
    }
}

@Composable
fun ArtistDetails(artistDetailsResponse: ArtistDetailsResponse) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        PageHeader(heading = artistDetailsResponse.artist.name)
        MediumSpacer()
        DisplayLargeImage(url = artistDetailsResponse.artist.image?.get(3)?.text ?: "")
        LargeSpacer()
        artistDetailsResponse.artist.stats?.let {
            Listener(listener = it.listeners)
        }
        MediumSpacer()
        artistDetailsResponse.artist.stats?.let {
            PlayCount(playCount = it.playcount)
        }
        MediumSpacer()
        artistDetailsResponse.artist.bio?.let {
            WikiOrBio(wikiOrBio = it.content)
        }
    }
}

@Composable
fun DisplayLargeImage(url: String) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = null,
        modifier = Modifier.size(250.dp)
    )
}