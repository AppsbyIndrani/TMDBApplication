package com.example.tmdbapplication.trendingmovie

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapplication.Credentials
import com.example.tmdbapplication.NetworkUtils
import com.example.tmdbapplication.db.MovieDatabase
import com.example.tmdbapplication.model.MovieList
import com.example.tmdbapplication.model.TrendingMovies
import com.example.tmdbapplication.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TrendingMoviesViewModel(
    private val movieRepository: MovieRepository
    , private val movieDatabase: MovieDatabase, private val context: Context
) : ViewModel()
{
    private val _movieData= MutableLiveData<TrendingMovies>()
    val movieData: LiveData<TrendingMovies>
        get() = _movieData

    val _errormessage=MutableLiveData<String>()
    val errormessage:LiveData<String>
    get() = _errormessage

    init {
        getMovieData()
    }

     fun getMovieData()
     {
         if (NetworkUtils.isInternetAvailable(context)) {
             viewModelScope.launch {
                 val response = movieRepository.getAllMoviesList(Credentials.API_KEY)
                 response.enqueue(object : Callback<TrendingMovies> {
                     override fun onResponse(
                         call: Call<TrendingMovies>,
                         response: Response<TrendingMovies>
                     ) {

                         viewModelScope.launch {
                             movieDatabase.movieDao().addMovies(response.body()!!.results)
                         }
                         _movieData.postValue(response.body())

                     }

                     override fun onFailure(call: Call<TrendingMovies>, t: Throwable) {

                         _errormessage.postValue(t.message)

                     }

                 })

             }
         }
         else
         {
             viewModelScope.launch {
                 val movie = movieDatabase.movieDao().getMovies()
                 val movielist=TrendingMovies(1,movie,1)
                 _movieData.postValue(movielist)
             }
         }





             /*enqueue(object : Callback<TrendingMovies> {
                     override fun onResponse(
                         call: Call<TrendingMovies>,
                         response: Response<TrendingMovies>
                     ) {
                         _movieData.value=response.body()
                     }

                     override fun onFailure(call: Call<TrendingMovies>, t: Throwable) {
                         Log.v("Response","Result : " +t.message)
                     }

                 })*/


    }
}