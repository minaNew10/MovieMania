package com.example.moviemania.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemania.domain.Movie
import com.example.moviemania.ui.MoviesAdapter


    @BindingAdapter("bindMovies")
    fun bindList(recyclerView: RecyclerView,movies :List<Movie>?){
        val adapter = recyclerView.adapter as MoviesAdapter
        adapter.submitList(movies)
    }
