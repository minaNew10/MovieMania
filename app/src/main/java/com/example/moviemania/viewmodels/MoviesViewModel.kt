package com.example.moviemania.viewmodels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.moviemania.domain.Movie
import com.example.moviemania.network.MoviesApi
import com.example.moviemania.network.asDomainModel
import com.example.moviemania.repository.MainRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.lang.Exception

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

