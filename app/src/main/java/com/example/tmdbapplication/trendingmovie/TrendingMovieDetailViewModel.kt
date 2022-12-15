package com.example.tmdbapplication.trendingmovie


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapplication.Credentials
import com.example.tmdbapplication.model.MovieCredits
import com.example.tmdbapplication.model.MovieData
import com.example.tmdbapplication.repository.MovieRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TrendingMovieDetailViewModel(private val repository: MovieRepository,movieID: Int) :ViewModel()
{
    private val _movieDetails=MutableLiveData<MovieData>()
    val movieDetails:LiveData<MovieData>
    get() = _movieDetails

    private val _movieCastData=MutableLiveData<MovieCredits>()
    val movieCastData:LiveData<MovieCredits>
    get() = _movieCastData

    init {

        getMovieDetails(movieID)
        getMovieCastData(movieID)

    }

     fun getMovieCastData(movieID: Int)
    {

        viewModelScope.launch {

            val response=repository.getMovieCredits(movieID,Credentials.API_KEY)
            response.enqueue(object : Callback<MovieCredits>
            {
                override fun onResponse(
                    call: Call<MovieCredits>,
                    response: Response<MovieCredits>
                ) {
                    _movieCastData.value=response.body()
                    Log.v("response", response.body().toString())

                }

                override fun onFailure(call: Call<MovieCredits>, t: Throwable)
                {
                    Log.v("response", t.message.toString())

                }


            })
        }

    }

    fun getMovieDetails(movieID: Int) {

        viewModelScope.launch {
            val response = repository.getMovieDetails(
                movieID,
                Credentials.API_KEY
            )
            response.enqueue(object : Callback<MovieData> {
                override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                    _movieDetails.value = response.body()

                    Log.v("response", response.body().toString())
                }

                override fun onFailure(call: Call<MovieData>, t: Throwable) {
                    Log.v("response", t.message.toString())
                }

            })
        }
    }


}