



package com.example.tmdbapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmdbapplication.adapter.TrendingMoviesAdapter
import com.example.tmdbapplication.databinding.ActivityMainBinding
import com.example.tmdbapplication.db.MovieDatabase
import com.example.tmdbapplication.model.MovieList
import com.example.tmdbapplication.repository.MovieRepository
import com.example.tmdbapplication.trendingmovie.TrendingMovieVMFactory
import com.example.tmdbapplication.trendingmovie.TrendingMoviesViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener,TrendingItemClicked {
    lateinit var binding:ActivityMainBinding
    lateinit var trendingMoviesAdapter: TrendingMoviesAdapter
    lateinit var trendingMoviesViewModel: TrendingMoviesViewModel
    val movieDetails : ArrayList<MovieList> = arrayListOf<MovieList>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtanimation=AnimationUtils.loadAnimation(this,R.anim.txt_anim)
        binding.txtTrending.startAnimation(txtanimation)

        trendingMoviesViewModel=ViewModelProvider(this,
           TrendingMovieVMFactory(MovieRepository(), MovieDatabase.getDatabase(this),
           this))
            .get(TrendingMoviesViewModel::class.java)

        initRv()

    }



    private fun initRv() {

        trendingMoviesViewModel.movieData.observe(this, Observer {

            if (it!=null)
            {
                movieDetails.addAll(it.results)
                trendingMoviesAdapter= TrendingMoviesAdapter(this,movieDetails,this)
                binding.rvMovieList.setHasFixedSize(true)
                binding.rvMovieList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,
                    false)
                binding.rvMovieList.adapter=trendingMoviesAdapter
            }

        })

    }
    override fun onClick(v: View?)
    {

    }

    override fun onItemClicked(item: MovieList)
    {
        val intent=Intent(this@MainActivity,MoviesDetailActivity::class.java)
        intent.putExtra("movieid",item.id)
        startActivity(intent)

    }
}