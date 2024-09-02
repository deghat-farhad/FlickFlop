package farhad.deghat.flickflop.movie_list.data.repository

import farhad.deghat.flickflop.movie_list.data.mapper.MoviePageEntityMapper
import farhad.deghat.flickflop.movie_list.data.remote.Remote
import farhad.deghat.flickflop.movie_list.domain.model.MoviesPage
import farhad.deghat.flickflop.movie_list.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remote: Remote,
    private val moviesPageEntityMapper: MoviePageEntityMapper
): MovieRepository {
    override suspend fun getPopularMovies(page: Int): Result<MoviesPage> =
        remote.getPopularMovies(page).map { moviesPageEntity -> moviesPageEntityMapper.mapToDomain(moviesPageEntity) }
}