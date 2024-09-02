package farhad.deghat.flickflop.movie_list.data.remote

import farhad.deghat.flickflop.movie_list.data.remote.services.MoviesService
import retrofit2.Retrofit
import javax.inject.Inject

class ServiceGenerator @Inject constructor(
    private val retrofit: Retrofit
) {
    val moviesService: MoviesService =   retrofit.create(MoviesService::class.java)
}