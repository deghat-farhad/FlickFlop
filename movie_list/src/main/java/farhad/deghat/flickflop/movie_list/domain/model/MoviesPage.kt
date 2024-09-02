package farhad.deghat.flickflop.movie_list.domain.model


data class MoviesPage(
    val page: Int,
    val movies: List<Movie>,
    val totalPages: Int,
    val totalResults: Int,
)