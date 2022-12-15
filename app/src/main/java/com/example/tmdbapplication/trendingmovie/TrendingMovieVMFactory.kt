package com.example.tmdbapplication.trendingmovie

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapplication.db.MovieDatabase
import com.example.tmdbapplication.repository.MovieRepository

class TrendingMovieVMFactory(
    private val movieRepository: MovieRepository
    , private val movieDatabase: MovieDatabase, private val applicationContext: Context
):
    ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrendingMoviesViewModel(movieRepository,movieDatabase,applicationContext) as T
    }

}