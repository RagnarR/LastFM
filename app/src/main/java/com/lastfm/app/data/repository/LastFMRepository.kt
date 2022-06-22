package com.lastfm.app.data.repository

import com.lastfm.app.data.api.dto.*

interface LastFMRepository {

    suspend fun getAlbums(albumName: String): AlbumsResponse

    suspend fun getAlbumDetails(albumName: String, artistName: String): AlbumDetailsResponse

    suspend fun getTopAlbums(artistName: String): TopAlbumsResponse

    suspend fun getArtists(artistName: String): ArtistsResponse

    suspend fun getArtistDetails(artistName: String): ArtistDetailsResponse

    suspend fun getTracks(trackName: String): TracksResponse

    suspend fun getTrackDetails(trackName: String, artistName: String): TrackDetailsResponse
}