package com.example.movierow.navi

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movierow.screens.detail.DetailScreen
import com.example.movierow.screens.fav.FavouriteScreen
import com.example.movierow.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(
            MovieScreens.HomeScreen.name
        ) {
            HomeScreen(
                navController = navController
            )
        }

        composable(
            MovieScreens.DetailScreen.name +"/{movie}",
            arguments = listOf(navArgument("movie") {
                type = NavType.StringType
            })
        ) {
            backStackEntry ->
            DetailScreen(
                navController = navController,
                backStackEntry.arguments?.getString("movie")
            )
        }

        composable(
            MovieScreens.FavouriteScreen.name
        ){
            FavouriteScreen(navController = navController)
        }



        // add more routes and screens here

    }
}