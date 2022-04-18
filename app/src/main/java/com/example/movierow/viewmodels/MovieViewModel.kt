package com.example.movierow.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movierow.models.Movie

class MovieViewModel : ViewModel() {
    private var movies = mutableStateListOf<Movie>()

    init {
        movies.addAll(
            listOf(
                /*TODO*/
            )
        )
    }

    fun addMovie(movie: Movie){
        movies.add(movie)
    }

    fun removeMovie(movie: Movie){
        movies.remove(movie)
    }

    fun getAllMovies(): List<Movie>{
        return movies
    }

    fun checkFavourite(movie: Movie): Boolean{
        return movies.contains(movie)

    }

}