package com.lastfm.app.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lastfm.app.ui.theme.LastFMTheme
import com.lastfm.app.ui.views.detailsscreen.AlbumDetailsWithViewModel
import com.lastfm.app.ui.views.detailsscreen.ArtistTopAlbumsWithViewModel
import com.lastfm.app.ui.views.listscreen.AlbumsListWithViewModel
import com.lastfm.app.ui.views.listscreen.ArtistsListWithViewModel
import com.lastfm.app.ui.views.reusable_views.LastFMWidgets
import com.lastfm.app.viewmodels.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val lastFMListViewModel: LastFMListViewModel by viewModels()
    private val lastFMAlbumDetailsViewModel: LastFMAlbumDetailsViewModel by viewModels()
    private val lastFMArtistTopAlbumsViewModel: LastFMArtistTopAlbumsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LastFMTheme {
                val navController = rememberNavController()
                val lastFMNavigation = LastFMNavigation(navController)
                DefaultSurface {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        LastFMWidgets(
                            navController = navController,
                            lastFMListViewModel = lastFMListViewModel
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = Screen.Home.route
                            ) {
                                composable(route = Screen.Home.route) {
                                    AlbumsListWithViewModel(
                                            lastFMListViewModel = lastFMListViewModel,
                                            lastFMNavigation = lastFMNavigation
                                        )
                                }
                                composable(
                                    route = Screen.AlbumDetails.route.plus("/{album}/{artist}"),
                                    arguments = albumDetailsScreenArguments
                                ) { navBackStackEntry ->

                                    val album = navBackStackEntry.arguments?.getString("album")
                                    val artist = navBackStackEntry.arguments?.getString("artist")

                                    DefaultSurface {
                                        if (artist != null && album != null) {
                                            AlbumDetailsWithViewModel(
                                                lastFMAlbumDetailsViewModel = lastFMAlbumDetailsViewModel,
                                                album = album,
                                                artist = artist
                                            )
                                        }
                                    }

                                }
                                composable(route = Screen.Search.route) {
                                        ArtistsListWithViewModel(
                                            lastFMListViewModel = lastFMListViewModel,
                                            lastFMNavigation = lastFMNavigation
                                        )
                                }
                                composable(
                                    route = Screen.TopAlbums.route.plus("/{artist}"),
                                    arguments = artistTopAlbumsScreenArguments
                                ) { navBackStackEntry ->

                                    val artist = navBackStackEntry.arguments?.getString("artist")

                                    if (artist != null) {
                                        ArtistTopAlbumsWithViewModel(
                                            lastFMArtistTopAlbumsViewModel = lastFMArtistTopAlbumsViewModel,
                                            lastFMNavigation = lastFMNavigation,
                                            artist = artist
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

// A surface container using the 'background' color from the theme
@Composable
fun DefaultSurface(content: @Composable () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        content.invoke()
    }
}




