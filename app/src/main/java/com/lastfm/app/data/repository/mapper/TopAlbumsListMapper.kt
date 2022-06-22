package com.lastfm.app.data.repository.mapper

import com.lastfm.app.data.api.dto.Album
import com.lastfm.app.data.api.dto.AlbumsResponse
import com.lastfm.app.data.api.dto.TopAlbumsResponse
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson

internal object TopAlbumsListMapper {

    @FromJson
    fun fromJson(albumsResponse: TopAlbumsResponse): TopAlbumsResponse {
        return albumsResponse
    }

    @ToJson
    @Suppress("UNUSED_PARAMETER", "unused")
    fun toJson(writer: JsonWriter, value: List<Album>?) {
        throw UnsupportedOperationException()
    }
}