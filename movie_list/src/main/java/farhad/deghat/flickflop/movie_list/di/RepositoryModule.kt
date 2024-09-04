package farhad.deghat.flickflop.movie_list.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import farhad.deghat.flickflop.movie_list.data.repository.MovieRepositoryImpl
import farhad.deghat.flickflop.movie_list.domain.repository.MovieRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}