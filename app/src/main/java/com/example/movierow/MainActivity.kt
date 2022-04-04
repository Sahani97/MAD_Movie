package com.example.movierow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movierow.navi.MovieNavigation
import com.example.movierow.screens.home.HomeScreen
import com.example.movierow.ui.theme.MovieRowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TopBar {
                MovieNavigation()
            }

        }
    }
}

@Composable
fun TopBar(content: @Composable () -> Unit) {
    MovieRowTheme{
        content()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieRowTheme() {
        HomeScreen()
    }

}