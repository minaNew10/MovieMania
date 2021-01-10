package com.example.moviemania.utils

import android.util.Log
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.moviemania.R
import com.example.moviemania.domain.Movie
import com.example.moviemania.network.MoviesApi
import com.example.moviemania.ui.DataItem
import com.example.moviemania.ui.MoviesAdapter


@BindingAdapter("bindMovies")
fun bindList(recyclerView: RecyclerView, movies: List<Movie>?) {
    val adapter = recyclerView.adapter as MoviesAdapter
    val items = when(movies){
        null -> listOf( DataItem.HeaderItem)
        else -> listOf(DataItem.HeaderItem) + movies.map { DataItem.MovieItem(it) }
    }
    adapter.submitList(items)
}

@BindingAdapter("bindImage")
fun bindImageUrl(imageView: ImageView,imgUrl:String){
    imgUrl?.let {
        val uri = MoviesApi.IMAGE_URL.plus(imgUrl).toUri().buildUpon().build()
        Glide.with(imageView.context)
            .load(uri)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply{
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_baseline_broken_image_24)
            }
            .into(imageView)
    }

}