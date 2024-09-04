package farhad.deghat.flickflop.movie_list.domain.repository

import androidx.paging.PagingData
import farhad.deghat.flickflop.movie_list.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<PagingData<Movie>>
}