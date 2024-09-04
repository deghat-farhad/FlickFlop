package farhad.deghat.flickflop.movie_list.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import farhad.deghat.flickflop.movie_list.data.entity.MovieEntity

class MoviePagingSource(
    private val remote: Remote
) : PagingSource<Int, MovieEntity>() {
    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        val currentPage = params.key
            ?: 1 //if params.key is null that means we didn't fetch any pages yet so we should fetch page one.
        val movies = remote.getPopularMovies(currentPage)
        movies
            .onSuccess { moviesPage ->
                return LoadResult.Page(
                    data = moviesPage.movies,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (currentPage <= moviesPage.totalPages) currentPage + 1 else null
                )
            }
            .onFailure { exception ->
                return LoadResult.Error(exception)
            }
        return LoadResult.Invalid()
    }
}