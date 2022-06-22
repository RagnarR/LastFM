package com.lastfm.app.data.api.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TopAlbumsResponse(
    @Json(name = "topalbums")
    val results: TopAlbumMatches
) : Parcelable