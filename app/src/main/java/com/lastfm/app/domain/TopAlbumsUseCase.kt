package com.lastfm.app.domain

import android.util.Log
import com.lastfm.app.data.api.dto.AlbumsResponse
import com.lastfm.app.data.api.dto.TopAlbumsResponse
import com.lastfm.app.data.repository.LastFMRepository
import com.lastfm.app.network.NetworkApiRepositoryException
import com.lastfm.app.network.Type
import javax.inject.Inject

class TopAlbumsUseCase @Inject constructor(
    private val lastFMRepository: LastFMRepository
) {

    suspend fun execute(artistName: String): UseCaseResult<TopAlbumsResponse> =
        try {
            val albums = lastFMRepository.getTopAlbums(artistName = artistName)
            UseCaseResult.SuccessResult(albums)
        } catch (networkApiRepositoryException: NetworkApiRepositoryException) {
            when (networkApiRepositoryException.type) {
                Type.Connection -> UseCaseResult.NetworkErrorResult
                else -> UseCaseResult.GenericErrorResult
            }
        }

}