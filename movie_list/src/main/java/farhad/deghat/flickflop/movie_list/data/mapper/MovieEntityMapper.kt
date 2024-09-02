package farhad.deghat.flickflop.movie_list.data.mapper

import farhad.deghat.flickflop.common.data.mapper.ToDomainMapper
import farhad.deghat.flickflop.movie_list.data.entity.MovieEntity
import farhad.deghat.flickflop.movie_list.domain.model.Movie
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() : ToDomainMapper<MovieEntity, Movie> {
    override fun mapToDomain(from: MovieEntity) = Movie(
        adult = from.adult,
        backdropPath = from.backdropPath,
        genreIds = from.genreIds,
        id = from.id,
        originalLanguage = from.originalLanguage,
        originalTitle = from.originalTitle,
        overview = from.overview,
        popularity = from.popularity,
        posterPath = from.posterPath,
        releaseDate = from.releaseDate,
        title = from.title,
        video = from.video,
        voteAverage = from.voteAverage,
        voteCount = from.voteCount,
    )
}