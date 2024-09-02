package farhad.deghat.flickflop.movie_list.domain.usecase.get_movies_page

import farhad.deghat.flickflop.common.domain.usecase.UseCaseWithParams
import farhad.deghat.flickflop.movie_list.domain.model.MoviesPage
import farhad.deghat.flickflop.movie_list.domain.repository.MovieRepository
import javax.inject.Inject

class GetMoviesPage @Inject constructor(
    private val moviesRepository: MovieRepository
) : UseCaseWithParams<Result<MoviesPage>, GetMoviesPageParams> {
    override suspend fun invoke(params: GetMoviesPageParams) =
        moviesRepository.getPopularMovies(params.page)
}