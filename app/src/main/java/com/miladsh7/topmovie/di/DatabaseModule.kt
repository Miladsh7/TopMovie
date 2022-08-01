package com.miladsh7.topmovie.di

import android.content.Context
import androidx.room.Room
import com.miladsh7.topmovie.db.MovieDatabase
import com.miladsh7.topmovie.db.MovieEntity
import com.miladsh7.topmovie.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, MovieDatabase::class.java, Constant.MOVIES_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: MovieDatabase) = db.movieDao()

    @Provides
    @Singleton
    fun provideEntity() = MovieEntity()
}