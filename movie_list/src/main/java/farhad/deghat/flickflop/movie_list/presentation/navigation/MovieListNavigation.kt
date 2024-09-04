package farhad.deghat.flickflop.movie_list.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import farhad.deghat.flickflop.movie_list.presentation.view.MovieListRoute

const val ROUTE_MOVIE_LIST = "poem_list_route"
fun NavGraphBuilder.movieList(
    navController: NavController
) {
    composable(ROUTE_MOVIE_LIST) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            MovieListRoute(
                viewModel = hiltViewModel(),
            )
        }
    }
}