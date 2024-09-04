package farhad.deghat.flickflop.movie_list.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import farhad.deghat.flickflop.movie_list.presentation.model.MovieItem
import farhad.deghat.flickflop.movie_list.presentation.viewmodel.MovieListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun MovieListScreen(
    moviePagingItems: LazyPagingItems<MovieItem>,
    imageBaseUrl: String,
    onRetry: () -> Unit,
    loadingState: MovieListViewModel.LoadingState
) {

    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(loadingState == MovieListViewModel.LoadingState.Error) {
        if (loadingState == MovieListViewModel.LoadingState.Error) {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = "Error",
                actionLabel = "retry",
                withDismissAction = false,
            )

            if (snackBarResult == SnackbarResult.ActionPerformed) {
                onRetry()
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { paddingValues ->
        Box(
            modifier =
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (loadingState == MovieListViewModel.LoadingState.FirstLoading) {
                LoadingItem(
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                LazyColumn {
                    items(moviePagingItems.itemCount) { index ->
                        moviePagingItems[index]?.let { movieItem ->
                            MovieItemUI(
                                movie = movieItem,
                                imageBaseUrl = imageBaseUrl,
                            )
                        }
                    }
                    if (loadingState == MovieListViewModel.LoadingState.ItemLoading) {
                        item { LoadingItem() }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    val sampleMovies = listOf(
        MovieItem(
            title = "Movie 1",
            overview = "Overview of Movie 1.",
            posterPath = "/static/develop/ui/compose/images/graphics-sourceimagesmall.jpg"
        ),
        MovieItem(
            title = "Movie 2",
            overview = "Overview of Movie 2.",
            posterPath = "/static/develop/ui/compose/images/graphics-sourceimagesmall.jpg"
        )
    )

    val sampleImageBaseUrl = "https://developer.android.com"

    val pagingItemsFlow: Flow<PagingData<MovieItem>> = flow {
        emit(PagingData.from(sampleMovies))
    }
    val lazyPagingItems = pagingItemsFlow.collectAsLazyPagingItems()

    MovieListScreen(
        moviePagingItems = lazyPagingItems,
        imageBaseUrl = sampleImageBaseUrl,
        onRetry = { /* No-op for preview */ },
        loadingState = MovieListViewModel.LoadingState.NotLoading
    )
}