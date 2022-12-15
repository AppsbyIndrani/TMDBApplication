package com.example.tmdbapplication

import com.example.tmdbapplication.model.Genres
import com.example.tmdbapplication.model.MovieList

interface TrendingItemClicked
{

    fun onItemClicked(item:MovieList)

}

fun getGeneres(list:ArrayList<Genres>) : String{

    var generes=" "

    for (i in list.indices)
    {

        generes=generes + "," + list[i].name

    }
    generes=generes.substring(2)
    return generes

}

fun getDate(date:String):String{

    val year:String
    val month:String
    val day:String

    year=date.substring(0,4)
    month=date.substring(5,7)
    day=date.substring(8,10)

    return day + "-" + month + "-" + year
}