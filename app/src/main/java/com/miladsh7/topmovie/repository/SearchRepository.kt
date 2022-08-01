package com.miladsh7.topmovie.repository


import com.miladsh7.topmovie.api.ApiServices
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: ApiServices
) {

    suspend fun searchMovie(name: String) = api.searchMovie(name)
}