package com.example.tmdbapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdbapplication.model.MovieList

@Database(entities = [MovieList::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase()
{
    abstract fun movieDao():TrendingMovieDao

    companion object
    {
        @Volatile
        private var INSTANCE:MovieDatabase?=null
        fun getDatabase(context: Context):MovieDatabase
        {
            if (INSTANCE==null)
            {
                synchronized(this){
                    INSTANCE= Room.databaseBuilder(context,MovieDatabase::class.java
                    ,"movieDB").build()
                }
            }
            return INSTANCE!!
        }


    }
}