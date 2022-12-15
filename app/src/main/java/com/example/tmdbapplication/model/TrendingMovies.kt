package com.example.tmdbapplication.model

import androidx.room.Entity


data class TrendingMovies(
    val page:Int,
    val results:List<MovieList>,
    val total_pages:Int
)
{

}


