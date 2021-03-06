package com.example.movierow.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movierow.models.Movie
import com.example.movierow.models.getMovies
import com.example.movierow.navi.MovieScreens
import com.example.movierow.ui.theme.MovieRowTheme
import com.example.movierow.viewmodels.MovieViewModel
import com.example.movierow.widget.MovieRow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movierow.widget.FavouriteIcon

@Composable
fun HomeScreen(
    viewModel: MovieViewModel = viewModel(),
    navController: NavController = rememberNavController()
) {
    var showMenu by remember {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(MovieScreens.FavouriteScreen.name) }) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favourites",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    text = "Favourites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp)
                                )
                            }

                        }
                    }

                }
            )
        }

    ) {
        MainContent(viewModel = viewModel, navController = navController)


    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainContent(
    viewModel: MovieViewModel = viewModel(),
    navController: NavController,
    movieList: List<Movie> = getMovies()
) {
    LazyColumn {
        items(movieList) { movies ->
            MovieRow(movie = movies, viewFavIconState = true, State = viewModel.checkFavourite(movies),
                onFavouriteClick = {
                    if (viewModel.checkFavourite(it)) {
                        viewModel.removeMovie(it)
                    }else{
                        viewModel.addMovie(it)
                    }
                },
                onItemClick = { movieId ->
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")

                }
            )
        }
    }
}