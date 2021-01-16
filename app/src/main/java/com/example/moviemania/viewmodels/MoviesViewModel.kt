package com.example.moviemania.viewmodels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.moviemania.domain.Movie
import com.example.moviemania.network.MoviesApi
import com.example.moviemania.network.asDomainModel
import com.example.moviemania.repository.MainRepository
import com.example.moviemania.work.RefreshDataWorker
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.concurrent.TimeUnit

private const val TAG = "MoviesViewModel"
@ExperimentalCoroutinesApi
class MoviesViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) : ViewModel() {

    val movies = mainRepository.movies
    init {
        getMovies()

    }

    private fun getMovies(){

        viewModelScope.launch {
            try {
              mainRepository.refreshMovies()
            }catch (e: Exception){

            }

        }
    }



}

