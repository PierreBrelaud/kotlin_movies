package com.gmail.eamosse.idbdata.api.service

import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.CategoryResponse
import com.gmail.eamosse.idbdata.api.response.MovieResponse
import com.gmail.eamosse.idbdata.api.response.TokenResponse
import com.gmail.eamosse.idbdata.api.response.TrendingPersonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieService {
    @GET("authentication/token/new")
    suspend fun getToken(): Response<TokenResponse>

    @GET("genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(@Query("with_genres") withGenres: Int): Response<MovieListResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") movie_id:Int): Response<MovieResponse>

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(): Response<TrendingMovieResponse>

    @GET("trending/person/week")
    suspend fun getTrendingPeople(): Response<TrendingPersonResponse>
}