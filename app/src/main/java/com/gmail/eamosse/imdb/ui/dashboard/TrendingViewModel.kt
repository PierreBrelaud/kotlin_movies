package com.gmail.eamosse.imdb.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.TrendingMovie
import com.gmail.eamosse.idbdata.data.TrendingPerson
import com.gmail.eamosse.idbdata.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.gmail.eamosse.idbdata.utils.Result

class TrendingViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _trendingMovies: MutableLiveData<List<TrendingMovie>> = MutableLiveData()
    val trendingMovies: LiveData<List<TrendingMovie>>
        get() = _trendingMovies

    private val _trendingPeople: MutableLiveData<List<TrendingPerson>> = MutableLiveData()
    val trendingPeople: LiveData<List<TrendingPerson>>
        get() = _trendingPeople

    private val _topCategories: MutableLiveData<List<Category>> = MutableLiveData()
    val topCategories: LiveData<List<Category>>
        get() = _topCategories

    init {
        loadTrendingPeople()
        loadTrendingMovies()
        loadTopCategories()
    }

    fun loadTrendingPeople() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTrendingPeople()) {
                is Result.Succes -> {
                    _trendingPeople.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun loadTrendingMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTrendingMovies()) {
                is Result.Succes -> {
                    _trendingMovies.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }

    fun loadTopCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategories()) {
                is Result.Succes -> {
                    _topCategories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }

    }


}