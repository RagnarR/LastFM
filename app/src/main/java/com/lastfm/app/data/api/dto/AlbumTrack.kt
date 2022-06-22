package com.lastfm.app.data.api.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class AlbumTrack(
    @Json(name = "name")
    val name: String,
    @Json(name = "artist")
    val artist: Artist,
    @Json(name = "url")
    val url: String
) : Parcelable {
    fun toTrack() = Track(
        name = name,
        artist = artist.name,
        image = emptyList()
    )
}