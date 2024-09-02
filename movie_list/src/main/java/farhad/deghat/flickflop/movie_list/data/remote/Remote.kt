package farhad.deghat.flickflop.movie_list.data.remote

import farhad.deghat.flickflop.movie_list.data.entity.MoviesPageEntity
import javax.inject.Inject

class Remote @Inject constructor (
    private val serviceGenerator: ServiceGenerator
) {
    suspend fun getPopularMovies(page: Int): Result<MoviesPageEntity> =
        serviceGenerator.moviesService.getPopularMovies(page)
}