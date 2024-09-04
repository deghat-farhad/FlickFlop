package farhad.deghat.flickflop.movie_list.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import farhad.deghat.flickflop.movie_list.data.mapper.MovieEntityMapper
import farhad.deghat.flickflop.movie_list.data.remote.MoviePagingSource
import farhad.deghat.flickflop.movie_list.data.remote.Remote
import farhad.deghat.flickflop.movie_list.domain.model.Movie
import farhad.deghat.flickflop.movie_list.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: Remote,
    private val movieEntityMapper: MovieEntityMapper,
) : MovieRepository {
    override suspend fun getPopularMovies(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
        ),
        pagingSourceFactory = {
            MoviePagingSource(remote)
        }
    ).flow.map { moviePagingData ->
        moviePagingData.map { movieEntity ->
            movieEntityMapper.mapToDomain(movieEntity)
        }
    }

    companion object {
        const val PAGE_SIZE = 15
        const val PREFETCH_DISTANCE = 2
    }
}