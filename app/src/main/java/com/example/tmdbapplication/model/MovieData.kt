package com.example.tmdbapplication.model

data class MovieData(
    val backdrop_path:String?,
    val genres:ArrayList<Genres>,
    val homepage:String?,
    val id:Int,
    val title:String,
    val overview:String?,
    val poster_path:String?,
    val release_date:String,
    val runtime:Int?,
    val status:String,
    val vote_average:Double) {

    }
