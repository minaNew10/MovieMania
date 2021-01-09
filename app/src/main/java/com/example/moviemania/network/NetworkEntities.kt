package com.example.moviemania.network

import com.example.moviemania.database.DatabaseMovie
import com.example.moviemania.domain.Movie
import com.squareup.moshi.Json


data class NetworkMovieContainer(var results: List<NetworkMovie>)

data class NetworkMovie(
    var id: Int,
    var title:String,
    @Json(name = "poster_path") val imageUrl: String,
    @Json(name = "vote_average") val voteAverage: Float
)
fun NetworkMovieContainer.asDatabaseModel() = results.map {
    DatabaseMovie(
        it.id,
        it.title,
        it.imageUrl,
        it.voteAverage
    )
}
fun NetworkMovieContainer.asDomainModel() = results.map {
    Movie(
        it.id,
        it.title,
        it.imageUrl,
        it.voteAverage
    )
}