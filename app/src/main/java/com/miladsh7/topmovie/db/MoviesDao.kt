package com.miladsh7.topmovie.db

import androidx.room.*
import com.miladsh7.topmovie.utils.Constant

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(entity: MovieEntity)

    @Delete
    suspend fun deleteMovie(entity: MovieEntity)

    @Query("SELECT * FROM ${Constant.MOVIES_TABLE}")
    fun getAllMovies(): MutableList<MovieEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM ${Constant.MOVIES_TABLE} WHERE id = :movieId)")
    suspend fun existMovie(movieId: Int):Boolean

}