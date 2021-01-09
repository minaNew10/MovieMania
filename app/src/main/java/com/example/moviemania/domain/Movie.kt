package com.example.moviemania.domain

import com.squareup.moshi.Json

data class Movie(
    val id: Int,
    val title:String,
    val imageUrl:String,
    val voteAverage:Float
)
