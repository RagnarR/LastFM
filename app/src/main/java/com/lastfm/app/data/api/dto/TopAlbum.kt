package com.lastfm.app.data.api.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TopAlbum(
    @Json(name = "name")
    val name: String,
    @Json(name = "artist")
    val artist: Artist,
    @Json(name = "listeners")
    val listeners: String?,
    @Json(name = "playcount")
    val playCount: String?,
    @Json(name = "image")
    val image: List<Image>,
    @Json(name = "wiki")
    val wiki: Wiki?
) : Parcelable {
    fun toAlbum() = Album(name, artist.name, listeners, playCount, image, null, wiki)
}