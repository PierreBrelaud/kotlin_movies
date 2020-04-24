package com.gmail.eamosse.idbdata.api.response

import com.gmail.eamosse.idbdata.data.Movie
import com.google.gson.annotations.SerializedName

internal data class MovieResponse(
    @SerializedName("id")
    val id:Int,
    @SerializedName("original_title")
    val original_title:String,
    @SerializedName("poster_path")
    val poster_path:String
)
internal fun MovieResponse.toMovie() = Movie(
    id = id,
    original_title = original_title,
    poster_path = poster_path
)