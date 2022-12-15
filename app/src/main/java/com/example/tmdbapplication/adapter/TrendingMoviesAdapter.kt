package com.example.tmdbapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapplication.MainActivity
import com.example.tmdbapplication.R
import com.example.tmdbapplication.model.MovieList

class
TrendingMoviesAdapter(var context:Context,val movielist:ArrayList<MovieList>,
                            var listener : MainActivity) :
    RecyclerView.Adapter<TrendingMoviesAdapter.MyViewHolder>()
{


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
      val moviePoster=itemView.findViewById<ImageView>(R.id.imgMoviePoster)
        val movieTitle=itemView.findViewById<TextView>(R.id.txtMovieName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.movielist_layout,parent,
            false)
        val viewHolder=MyViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(movielist[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val current=movielist[position]
        holder.movieTitle.text=current.title
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + current.poster_path)
            .into(holder.moviePoster)


    }

    override fun getItemCount(): Int {
        return movielist.size
    }

}