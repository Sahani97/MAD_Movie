package com.example.movierow.screens.fav

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movierow.models.getMovies
import com.example.movierow.screens.detail.MainContent
import com.example.movierow.screens.detail.filterMovie
import com.example.movierow.widget.MovieRow


@Preview(showBackground = true)
@Composable
fun FavouriteScreen(
    navController: NavController = rememberNavController()
){
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

                    Text(text = "My Favourite Movies")
                }
            }
        }

    ) {
        FavouriteScreenContent()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable

fun FavouriteScreenContent(){
    Column {
        MovieRow(movie = getMovies()[0])
        MovieRow(movie = getMovies()[1])
    }
}
