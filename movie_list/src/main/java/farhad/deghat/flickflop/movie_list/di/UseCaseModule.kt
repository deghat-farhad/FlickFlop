package farhad.deghat.flickflop.movie_list.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import farhad.deghat.flickflop.movie_list.domain.repository.MovieRepository
import farhad.deghat.flickflop.movie_list.domain.usecase.get_movies_page.GetMoviesPage

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Provides
    fun provideGetMoviesPage(
        movieRepository: MovieRepository
    ): GetMoviesPage = GetMoviesPage(
        movieRepository,
    )
}