package com.example.moviemania.network

import com.example.moviemania.database.DatabaseMovie
import com.example.moviemania.domain.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkMovieContainer(val results: List<NetworkMovie>)

@JsonClass(generateAdapter = true)
data class NetworkMovie(
    var id: Int,
    var title:String,
    @Json(name = "poster_path") val imageUrl: String,
    @Json(name = "vote_average") val voteAverage: Float
)
fun NetworkMovieContainer.asDatabaseModel() = results.map {
    DatabaseMovie(
        id = it.id,
        title =  it.title,
        imageUrl = it.imageUrl,
        voteAverage = it.voteAverage
    )
}
fun NetworkMovieContainer.asDomainModel() = results.map {
    Movie(
        id = it.id,
        title = it.title,
        imageUrl = it.imageUrl,
        voteAverage = it.voteAverage
    )
}