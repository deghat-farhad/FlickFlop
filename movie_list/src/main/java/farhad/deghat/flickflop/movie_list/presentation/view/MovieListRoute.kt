package farhad.deghat.flickflop.movie_list.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import farhad.deghat.flickflop.movie_list.presentation.viewmodel.MovieListViewModel

@Composable
fun MovieListRoute(
    viewModel: MovieListViewModel,
) {
    val uiState = viewModel.uiState.collectAsState()
    val moviePagingItems =
        (uiState.value as? MovieListViewModel.UiState.Ready)?.moviePagingData?.collectAsLazyPagingItems()
    LaunchedEffect(key1 = true) {
        viewModel.viewIsReady()
    }

    LaunchedEffect(moviePagingItems?.loadState) {
        moviePagingItems?.let {
            viewModel.onLoadStateChange(moviePagingItems.loadState)
        }
    }

    DisposableEffect(uiState.value) {
        (uiState.value as? MovieListViewModel.UiState.Ready)?.let { uiStateSnapshot ->
            uiStateSnapshot.events.forEach { event ->
                when (event) {
                    MovieListViewModel.Event.Retry -> {
                        moviePagingItems?.retry()
                    }
                }
                viewModel.onEventConsumed(event)
            }
        }
        onDispose { }
    }

    when (val stateValue = uiState.value) {
        MovieListViewModel.UiState.GettingReady -> {
            LoadingItem(modifier = Modifier.fillMaxSize())
        }

        is MovieListViewModel.UiState.Ready ->
            moviePagingItems?.let {
                MovieListScreen(
                    moviePagingItems = moviePagingItems,
                    imageBaseUrl = stateValue.imageBaseUrl,
                    onRetry = viewModel::retry,
                    loadingState = stateValue.loadingState
                )
            }
    }
}