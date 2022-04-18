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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movierow.models.Movie
import com.example.movierow.models.getMovies
import com.example.movierow.viewmodels.MovieViewModel
import com.example.movierow.widget.HorizontalScrollableImageView
import com.example.movierow.widget.MovieRow

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun DetailScreen(
    viewModel: MovieViewModel = viewModel(),
    navController: NavController = rememberNavController(),
    movieId: String? = "tt0499549"
){
    val movie = filterMovie(movieId = movieId)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Cyan,
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
        MainContent(viewModel = viewModel, movie = movie)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable

fun MainContent(
    viewModel: MovieViewModel = viewModel(),
    movie: Movie
) {

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Column {
            MovieRow(
                movie = movie, viewFavIconState = true, State = viewModel.checkFavourite(movie),
                onFavouriteClick = {
                    if (viewModel.checkFavourite(it)) {
                        viewModel.removeMovie(it)
                    }else{
                        viewModel.addMovie(it)
                    }
                }

            )

            Spacer(
                modifier = Modifier
                    .height(8.dp)
            )

            Divider()

            Text(text = "Movie Images",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            HorizontalScrollableImageView(movie = movie)
        }
    }
}

fun filterMovie(movieId: String?) : Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}



