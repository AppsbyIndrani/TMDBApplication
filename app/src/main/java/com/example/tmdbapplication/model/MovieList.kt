package com.example.tmdbapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="trendingmovie")
data class MovieList(
    @PrimaryKey(autoGenerate = true)
    val movieid:Int,
    val id:Int,
    val poster_path:String?,
    val title:String
)
{

}
