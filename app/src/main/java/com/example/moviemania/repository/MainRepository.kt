package com.example.moviemania.repository

import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.moviemania.database.MovieDao
import com.example.moviemania.database.asDomainModel
import com.example.moviemania.domain.Movie
import com.example.moviemania.network.MoviesApi
import com.example.moviemania.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import javax.inject.Inject

private const val TAG = "MainRepository"
class MainRepository @Inject constructor(
    val dao: MovieDao,
    val api: MoviesApi,
) {
    val movies : LiveData<List<Movie>> = Transformations.map(dao.getMovies()){
        it.asDomainModel()
    }
    suspend fun refreshMovies() {
        withContext(Dispatchers.IO){
            val playlist = api.getPopularMovies().await()
            Log.i(TAG, "refreshMovies: $playlist")
            dao.insertAll(playlist.asDatabaseModel())
        }

    }
}