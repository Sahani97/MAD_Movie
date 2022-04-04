package com.example.movierow.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.movierow.models.Movie
import com.example.movierow.models.getMovies

@OptIn(ExperimentalAnimationApi::class)
@ExperimentalMaterialApi
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = {}
) {


    var expandedState by remember { mutableStateOf(false) }
    /*val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )*/

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp,

        /*onClick = {
            expandedState = !expandedState
        }*/
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier.size(100.dp)) {
               /* Icon(imageVector = Icons.Default.AccountBox,
                    contentDescription = "movie pic")
                */
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = null
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)

            ) {

                Text(
                    text = movie.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = "Director: ${movie.director}",
                    style = MaterialTheme.typography.caption)
                Text(
                    text = "Released: ${movie.year}",
                    style = MaterialTheme.typography.caption)

                if(!expandedState){
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "arrow-down",
                        modifier = Modifier
                            .clickable(onClick = {expandedState = !expandedState})
                    )
                }else{
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "arrow-up",
                        modifier = Modifier
                            .clickable(onClick = {expandedState = !expandedState})
                    )
                }

                /*
                IconButton(
                    modifier = Modifier
                        .rotate(rotationState)
                        .weight(1f),
                    onClick = {
                        expandedState = !expandedState
                    }){
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription =
                    )
                }*/
                AnimatedVisibility(visible = expandedState){
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = "Plot: ${movie.plot}",
                            fontSize = MaterialTheme.typography.subtitle2.fontSize,
                            overflow = TextOverflow.Ellipsis
                        )
                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            fontSize = MaterialTheme.typography.subtitle2.fontSize,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Genre: ${movie.genre}",
                            fontSize = MaterialTheme.typography.subtitle2.fontSize,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            fontSize = MaterialTheme.typography.subtitle2.fontSize,
                            overflow = TextOverflow.Ellipsis
                        )
                    }


                }


            }
        }
    }
}