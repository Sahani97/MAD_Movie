package com.example.movierow.screens.fav

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movierow.models.getMovies
import com.example.movierow.screens.detail.MainContent
import com.example.movierow.screens.detail.filterMovie
import com.example.movierow.viewmodels.MovieViewModel
import com.example.movierow.widget.MovieRow


@Preview(showBackground = true)
@Composable
fun FavouriteScreen(
    viewModel: MovieViewModel = viewModel(),
    navController: NavController = rememberNavController()
){
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

                    Text(text = "My Favourite Movies")
                }
            }
        }

    ) {
        FavouriteScreenContent(viewModel = viewModel)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable

fun FavouriteScreenContent(
    viewModel: MovieViewModel = viewModel()
){
    var favouriteMovieList = viewModel.getAllMovies()
    LazyColumn {
        items(favouriteMovieList){ movies ->
            MovieRow(movie = movies, viewFavIconState = false, State = viewModel.checkFavourite(movies))
        }

    }
}
