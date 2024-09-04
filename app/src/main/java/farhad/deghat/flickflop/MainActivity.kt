package farhad.deghat.flickflop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import farhad.deghat.flickflop.movie_list.presentation.navigation.ROUTE_MOVIE_LIST
import farhad.deghat.flickflop.movie_list.presentation.navigation.movieList
import farhad.deghat.flickflop.ui.theme.FlickFlopTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            FlickFlopTheme {
                NavHost(
                    navController = navController,
                    startDestination = ROUTE_MOVIE_LIST,
                ) {
                    movieList(navController)
                }
            }
        }
    }
}