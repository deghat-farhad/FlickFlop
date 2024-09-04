package farhad.deghat.flickflop.movie_list.presentation.mapper

import farhad.deghat.flickflop.movie_list.domain.model.Movie
import farhad.deghat.flickflop.movie_list.presentation.model.MovieItem
import javax.inject.Inject

class MovieItemMapper @Inject constructor() {
    fun mapToPresentation(movie: Movie) =
        MovieItem(
            overview = movie.overview,
            title = movie.title,
            posterPath = movie.posterPath,
        )
}