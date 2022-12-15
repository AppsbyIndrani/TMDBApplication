package com.example.tmdbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.tmdbapplication.adapter.MovieCastAdapter
import com.example.tmdbapplication.databinding.ActivityMoviesDetailBinding
import com.example.tmdbapplication.model.MovieCredits
import com.example.tmdbapplication.model.MovieData
import com.example.tmdbapplication.repository.MovieRepository
import com.example.tmdbapplication.trendingmovie.MovieDetailsVMFactory
import com.example.tmdbapplication.trendingmovie.TrendingMovieDetailViewModel

class MoviesDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityMoviesDetailBinding
    lateinit var moviedata: MovieData
    lateinit var movieCredits: MovieCredits
    lateinit var movieCastAdapter: MovieCastAdapter
    lateinit var trendingMovieDetailViewModel: TrendingMovieDetailViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMoviesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId=intent.getIntExtra("movieid",0)

        val movieDetailsVMFactory=MovieDetailsVMFactory(MovieRepository(),movieId)

        trendingMovieDetailViewModel=ViewModelProvider(this,movieDetailsVMFactory)
            .get(TrendingMovieDetailViewModel::class.java)

        trendingMovieDetailViewModel.movieDetails.observe(this,Observer{
                //Toast.makeText(this,"Response: ${it.release_date}",Toast.LENGTH_SHORT).show()

            if(it!=null)
            {
                moviedata=it
                setUpElements()
            }

            })

        trendingMovieDetailViewModel.movieCastData.observe(this, Observer {

            if (it!=null)
            {
                movieCredits=it
                setRecyclerView()

            }

        })



    }

    fun setRecyclerView()
    {
        movieCastAdapter= MovieCastAdapter(this,movieCredits.cast)
        binding.rvMovieCastDetails.setHasFixedSize(true)
        binding.rvMovieCastDetails.layoutManager=LinearLayoutManager(this,
        LinearLayoutManager.HORIZONTAL,false)
        binding.rvMovieCastDetails.adapter=movieCastAdapter

    }

    private fun setUpElements() {

        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + moviedata.backdrop_path)
            .into(binding.kvMovieImage)
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" +moviedata.poster_path)
            .into(binding.imgMdMoviePoster)
        binding.txtMovieDetail.text=moviedata.overview
        binding.txtMdGenres.text= getGeneres(moviedata.genres)
        binding.txtMdMovieName.text=moviedata.title
        binding.txtReleaseDate.text= getDate(moviedata.release_date)
        binding.txtRunTime.text= moviedata.runtime.toString() + " mins"
        binding.rbMdRating.numStars=moviedata.vote_average.toInt() + 1
        binding.rbMdRating.rating= moviedata.vote_average.toFloat()
        binding.txtMdRating.text= moviedata.vote_average.toString()

    }
}