package com.example.moviemania.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DatabaseMovie::class],version = 1)
abstract class MoviesDatabase : RoomDatabase(){
    abstract val movieDao: MovieDao

    companion object{
        val DATABASE_NAME:String = "movies_db"
    }
}