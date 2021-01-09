package com.example.moviemania.utils

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviemania.domain.Movie
import com.example.moviemania.ui.MoviesAdapter

private const val TAG = "BindingAdapters"
    @BindingAdapter("bindMovies")
    fun bindList(recyclerView: RecyclerView,movies :List<Movie>?){
        val adapter = recyclerView.adapter as MoviesAdapter
        Log.i(TAG, "bindList: $movies")
        adapter.submitList(movies)
    }
