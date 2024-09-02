package farhad.deghat.flickflop.movie_list.domain.repository

import farhad.deghat.flickflop.movie_list.domain.model.MoviesPage

interface MovieRepository {
        suspend fun getPopularMovies(page: Int): Result<MoviesPage>
}