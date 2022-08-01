package com.miladsh7.topmovie.repository


import com.miladsh7.topmovie.api.ApiServices
import com.miladsh7.topmovie.db.MovieEntity
import com.miladsh7.topmovie.db.MoviesDao

import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val api: ApiServices,
    private val dao: MoviesDao
) {
    //Api
    suspend fun detailMovie(id: Int) = api.detailMovie(id)

    //Database
    suspend fun insertMovie(entity: MovieEntity) = dao.insertMovie(entity)
    suspend fun deleteMovie(entity: MovieEntity) = dao.deleteMovie(entity)
    suspend fun existsMovie(id: Int) = dao.existMovie(id)
}