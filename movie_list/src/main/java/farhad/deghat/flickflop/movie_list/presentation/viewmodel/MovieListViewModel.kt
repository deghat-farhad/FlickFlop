package farhad.deghat.flickflop.movie_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import farhad.deghat.flickflop.common.data.IMAGE_BASE_URL_NAME
import farhad.deghat.flickflop.movie_list.domain.usecase.get_movies_page.GetMoviesPage
import farhad.deghat.flickflop.movie_list.presentation.mapper.MovieItemMapper
import farhad.deghat.flickflop.movie_list.presentation.model.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesPage: GetMoviesPage,
    @Named(IMAGE_BASE_URL_NAME)
    private val imageBaseUrl: String,
    private val movieItemMapper: MovieItemMapper
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.GettingReady)
    val uiState: StateFlow<UiState> = _uiState

    fun viewIsReady() {
        viewModelScope.launch {
            _uiState.value = UiState.Ready(
                moviePagingData = getMoviesPage().map { moviePagingData ->
                    moviePagingData.map { movie ->
                        movieItemMapper.mapToPresentation(movie)
                    }
                },
                imageBaseUrl = imageBaseUrl,
                loadingState = LoadingState.NotLoading,
            )
        }
    }

    fun retry() {
        consumeEvent(Event.Retry)
        (_uiState.value as? UiState.Ready)?.let { uiStateSnapshot ->
            _uiState.value = uiStateSnapshot.copy(loadingState = LoadingState.NotLoading)
        }
    }

    fun onEventConsumed(
        eventConsumed: Event,
    ) {
        (_uiState.value as? UiState.Ready)?.let { uiStateSnapshot ->
            _uiState.value = uiStateSnapshot.copy(
                events = uiStateSnapshot.events.filterNot { event ->
                    event == eventConsumed
                },
            )
        }
    }

    private fun consumeEvent(
        eventToConsume: Event,
    ) {
        (_uiState.value as? UiState.Ready)?.let { uiStateSnapshot ->
            _uiState.value = uiStateSnapshot.copy(
                events = (uiStateSnapshot.events + eventToConsume)
            )
        }
    }

    fun onLoadStateChange(combinedLoadStates: CombinedLoadStates) {
        (_uiState.value as? UiState.Ready)?.let { uiStateSnapshot ->
            when {
                combinedLoadStates.refresh is LoadState.Error -> _uiState.value =
                    uiStateSnapshot.copy(loadingState = LoadingState.Error)

                combinedLoadStates.refresh == LoadState.Loading -> _uiState.value =
                    uiStateSnapshot.copy(loadingState = LoadingState.FirstLoading)

                (combinedLoadStates.refresh is LoadState.NotLoading && uiStateSnapshot.loadingState == LoadingState.FirstLoading) ->
                    _uiState.value = uiStateSnapshot.copy(loadingState = LoadingState.NotLoading)

                combinedLoadStates.append is LoadState.Error -> _uiState.value =
                    uiStateSnapshot.copy(loadingState = LoadingState.Error)

                combinedLoadStates.append == LoadState.Loading -> _uiState.value =
                    uiStateSnapshot.copy(loadingState = LoadingState.ItemLoading)

                (combinedLoadStates.append is LoadState.NotLoading && uiStateSnapshot.loadingState != LoadingState.FirstLoading) ->
                    _uiState.value = uiStateSnapshot.copy(loadingState = LoadingState.NotLoading)
            }
        }
    }

    sealed class UiState {
        data object GettingReady : UiState()
        data class Ready(
            val moviePagingData: Flow<PagingData<MovieItem>>,
            val imageBaseUrl: String,
            val events: List<Event> = emptyList(),
            val loadingState: LoadingState
        ) : UiState()
    }

    sealed class Event {
        data object Retry : Event()
    }

    sealed class LoadingState {
        data object FirstLoading : LoadingState()
        data object ItemLoading : LoadingState()
        data object Error : LoadingState()
        data object NotLoading : LoadingState()
    }
}