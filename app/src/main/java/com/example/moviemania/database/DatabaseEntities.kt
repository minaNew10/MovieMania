package com.example.moviemania.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviemania.domain.Movie

@Entity(tableName = "movies")
data class DatabaseMovie(
    @PrimaryKey
    val id :Int,
    val title: String,
    val imageUrl:String,
    val voteAverage:Float
)
fun List<DatabaseMovie>.asDomainModel() = map {
    Movie(
        it.id,
        it.title,
        it.imageUrl,
        it.voteAverage
    )
}