package com.example.tmdbapplication.trendingmovie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapplication.repository.MovieRepository

class MovieDetailsVMFactory(private val repository: MovieRepository,val movieId:Int) :
    ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrendingMovieDetailViewModel(repository,movieId) as T
    }

}