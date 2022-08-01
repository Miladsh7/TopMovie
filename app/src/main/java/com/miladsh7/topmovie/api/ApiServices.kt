package com.miladsh7.topmovie.api

import com.miladsh7.topmovie.model.detail.ResponseDetail
import com.miladsh7.topmovie.model.home.ResponseGenreList
import com.miladsh7.topmovie.model.home.ResponseMoviesList
import com.miladsh7.topmovie.model.register.BodyRegister
import com.miladsh7.topmovie.model.register.ResponseRegister
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @POST("register")
    suspend fun registerUser(@Body body: BodyRegister): Response<ResponseRegister>

    @GET("genres/{genre_id}/movies")
    suspend fun moviesTopList(@Path("genre_id") id: Int): Response<ResponseMoviesList>

    @GET("genres")
    suspend fun genreList(): Response<ResponseGenreList>

    @GET("movies")
    suspend fun moviesLastList(): Response<ResponseMoviesList>

    @GET("movies")
    suspend fun searchMovie(@Query("q") name: String): Response<ResponseMoviesList>

    @GET("movies/{movie_id}")
    suspend fun detailMovie(@Path("movie_id") id: Int): Response<ResponseDetail>
}