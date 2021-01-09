package com.example.moviemania.network

import com.example.moviemania.BuildConfig
import com.example.moviemania.domain.Movie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface MoviesApi {
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val IMAGE_URL="https://image.tmdb.org/t/p/w200"

        const val USER_API_KEY = BuildConfig.MOVIES_API_KEY
    }
    @GET("movie/popular?api_key=$USER_API_KEY")
    fun getPopularMovies(): Deferred<NetworkMovieContainer>
}