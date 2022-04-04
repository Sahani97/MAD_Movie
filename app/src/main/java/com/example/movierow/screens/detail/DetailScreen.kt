package com.example.movierow.screens.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movierow.models.Movie
import com.example.movierow.models.getMovies
import com.example.movierow.widget.MovieRow

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "tt0499549"
){
    val movie = filterMovie(movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar(

                backgroundColor = Color.Gray,
                elevation = 3.dp
            ){
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "up-button",
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(
                        modifier = Modifier
                            .width(20.dp)
                    )

                    Text(text = movie.title)
                }
            }
        }

    ) {
        MainContent(movie = movie)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable

fun MainContent(movie: Movie) {
    MovieRow(movie = movie)

    Spacer(
        modifier = Modifier
            .height(8.dp)
    )

    Divider()

}

fun filterMovie(movieId: String?) : Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}



