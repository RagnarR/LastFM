package com.lastfm.app.data.api.dto

import android.os.Parcelable
import com.lastfm.app.ui.views.Screen
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TopAlbumMatches(
    @Json(name = "album")
    val album: List<TopAlbum>
) : Parcelable