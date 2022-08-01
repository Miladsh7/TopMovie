package com.miladsh7.topmovie.repository

import com.miladsh7.topmovie.api.ApiServices
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: ApiServices
) {

    suspend fun topMoviesList(id: Int) = api.moviesTopList(id)

    suspend fun genreList() = api.genreList()

    suspend fun lastMoviesList() = api.moviesLastList()


}