package com.example.tmdbapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapplication.R
import com.example.tmdbapplication.model.Cast

class MovieCastAdapter(private val context: Context,
                       private val castArray : ArrayList<Cast>) : RecyclerView.Adapter<MovieCastAdapter.MyViewHolder>()
{

    inner class MyViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)
    {

        val image=itemView.findViewById<ImageView>(R.id.imgCast)
        val realname=itemView.findViewById<TextView>(R.id.txtCastName)
        val charactername=itemView.findViewById<TextView>(R.id.txtCharacterName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val view=LayoutInflater.from(parent.context).
        inflate(R.layout.cast_layout,parent,false)

        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)
    {
      val current=castArray[position]
      holder.realname.text=current.name
      holder.charactername.text=current.character
      Glide.with(context).load("https://image.tmdb.org/t/p/w500/" +current.profile_path)
          .into(holder.image)

    }

    override fun getItemCount(): Int
    {
        return castArray.size

    }


}