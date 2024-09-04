package farhad.deghat.flickflop.movie_list.data.remote.services

import farhad.deghat.flickflop.movie_list.data.entity.MoviesPageEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = "YOUR_API_KEY_HERE",
        @Query("sort_by") sortBy: String = "popularity.des"
    ): Result<MoviesPageEntity>
}