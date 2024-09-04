package farhad.deghat.flickflop.movie_list.domain.usecase.get_movies_page

import androidx.paging.PagingData
import farhad.deghat.flickflop.common.domain.usecase.UseCase
import farhad.deghat.flickflop.movie_list.domain.model.Movie
import farhad.deghat.flickflop.movie_list.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesPage(
    private val moviesRepository: MovieRepository
) : UseCase<Flow<PagingData<Movie>>> {
    override suspend fun invoke() =
        moviesRepository.getPopularMovies()
}