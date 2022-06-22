package com.lastfm.app.ui.views

import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.lastfm.app.R
import com.lastfm.app.ui.views.Screen.Companion.KEY_ALBUM_NAME
import com.lastfm.app.ui.views.Screen.Companion.KEY_ARTIST_NAME

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen(KEY_HOME, R.string.home)
    object Search : Screen(KEY_SEARCH, R.string.search)
    object TopAlbums : Screen(KEY_TOP_ALBUMS, R.string.top_albums)
    object AlbumDetails : Screen(KEY_ALBUM_DETAILS, R.string.album_details)

    fun withArgs(vararg args: String?): String = buildString {
        append(route)
        args.forEach { arg ->
            append("/$arg")
        }
    }

    companion object {
        const val KEY_ALBUM_NAME = "album"
        const val KEY_ARTIST_NAME = "artist"
        private const val KEY_SEARCH = "search"
        private const val KEY_HOME = "home"
        private const val KEY_TOP_ALBUMS = "topalbums"
        private const val KEY_ALBUM_DETAILS = "albumdetails"
    }
}

val albumDetailsScreenArguments = listOf(
    navArgument(KEY_ALBUM_NAME) {
        type = NavType.StringType
        defaultValue = "rock"
    },
    navArgument(KEY_ARTIST_NAME) {
        type = NavType.StringType
        defaultValue = "rock"
    }
)

val artistTopAlbumsScreenArguments = listOf(
    navArgument(KEY_ARTIST_NAME) {
        type = NavType.StringType
        defaultValue = "rock"
    }
)

