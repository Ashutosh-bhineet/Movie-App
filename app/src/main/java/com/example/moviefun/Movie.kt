package com.example.moviefun

import java.io.Serializable

data class Movie(
    val title:String,
    val imageName:String,
    val description:String,
    val director:String,
    val starCast: List<String>,
    val releaseDate:String,
    val imdbRating:String
):Serializable
