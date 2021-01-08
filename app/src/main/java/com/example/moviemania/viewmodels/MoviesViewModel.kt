package com.example.moviemania.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviemania.domain.Movie

class MoviesViewModel : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
        val movies: LiveData<List<Movie>>
            get() = _movies
    init {
        _movies.value = listOf(
            Movie("Toy Story",""),
            Movie("Patch Adams",""),
            Movie("Christopher robin",""),
            Movie("interstellar",""),
            Movie("happiness",""),
            Movie("Toy story 2",""),
            Movie("Lion King",""),
            Movie("Asterix",""),

        )
    }
}