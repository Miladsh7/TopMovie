package com.miladsh7.topmovie.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miladsh7.topmovie.model.home.ResponseGenreList
import com.miladsh7.topmovie.model.home.ResponseMoviesList
import com.miladsh7.topmovie.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    val topMoviesList = MutableLiveData<ResponseMoviesList>()
    val genresList = MutableLiveData<ResponseGenreList>()
    val lastMoviesList = MutableLiveData<ResponseMoviesList>()
    val loading = MutableLiveData<Boolean>()


    fun loadTopMoviesList(id: Int) = viewModelScope.launch {
        val response = repository.topMoviesList(id)
        if (response.isSuccessful) {
            topMoviesList.postValue(response.body())
        }
    }

    fun loadGenresList() = viewModelScope.launch {
        val response = repository.genreList()
        if (response.isSuccessful) {
            genresList.postValue(response.body())
        }
    }

    fun loadLastMoviesList() = viewModelScope.launch {
        loading.postValue(true)
        val response = repository.lastMoviesList()
        if (response.isSuccessful) {
            lastMoviesList.postValue(response.body())
        }
        loading.postValue(false)
    }

}