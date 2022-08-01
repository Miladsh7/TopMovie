package com.miladsh7.topmovie.repository

import com.miladsh7.topmovie.db.MoviesDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val dao: MoviesDao
) {
    fun allFavoriteList() = dao.getAllMovies()
}