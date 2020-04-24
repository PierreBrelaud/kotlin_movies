package com.gmail.eamosse.idbdata.repository

import android.util.Log
import com.gmail.eamosse.idbdata.api.response.*
import com.gmail.eamosse.idbdata.api.response.toCategory
import com.gmail.eamosse.idbdata.api.response.toEntity
import com.gmail.eamosse.idbdata.api.response.toMovie
import com.gmail.eamosse.idbdata.api.response.toToken
import com.gmail.eamosse.idbdata.data.*
import com.gmail.eamosse.idbdata.datasources.LocalDataSource
import com.gmail.eamosse.idbdata.datasources.OnlineDataSource
import com.gmail.eamosse.idbdata.utils.Result
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * La classe permettant de gérer les données de l'application
 */
class MovieRepository : KoinComponent {
    //Gestion des sources de données locales
    private val local: LocalDataSource by inject()
    //Gestion des sources de données en lignes
    private val online: OnlineDataSource by inject()

    /**
     * Récupérer le token permettant de consommer les ressources sur le serveur
     * Le résultat du datasource est converti en instance d'objets publiques
     */
    suspend fun getToken(): Result<Token> {
        return when (val result = online.getToken()) {
            is Result.Succes -> {
                //save the response in the local database
                local.saveToken(result.data.toEntity())
                //return the response
                Result.Succes(result.data.toToken())
            }
            is Result.Error -> result
        }
    }

    suspend fun getCategories(): Result<List<Category>> {
        return when (val result = online.getCategories()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    Log.i("category", "category : ${it.toCategory().name}")
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMoviesByGenre(withGenres: Int): Result<List<Movie>> {
        return when (val result = online.getMoviesByGenre(withGenres)) {
            is Result.Succes -> {
                val movies = result.data.map {
                    it.toMovie()
                }
                Result.Succes(movies)
            }
            is Result.Error -> result
        }
    }

    suspend fun getMovie(id:Int): Result<Movie> {
        return when(val result = online.getMovie(id)){
            is Result.Succes -> {
                val movie = result.data.toMovie()
                Result.Succes(movie)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTrendingMovies(): Result<List<TrendingMovie>> {
        return when (val result = online.getTrendingMovies()) {
            is Result.Succes -> {
                val trending = result.data.map {
                    it.toMovie()
                }
                Result.Succes(trending)
            }
            is Result.Error -> result
        }
    }

    suspend fun getTrendingPeople(): Result<List<TrendingPerson>> {
        return when (val result = online.getTrendingPeople()) {
            is Result.Succes -> {
                val people = result.data.map {
                    it.toPerson()
                }
                Result.Succes(people)
            }
            is Result.Error -> result
        }
    }

}