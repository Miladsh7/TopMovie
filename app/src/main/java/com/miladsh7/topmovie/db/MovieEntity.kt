package com.miladsh7.topmovie.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miladsh7.topmovie.utils.Constant

@Entity(tableName = Constant.MOVIES_TABLE)
data class MovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var poster: String = "",
    var title: String = "",
    var rate: String = "",
    var country: String = "",
    var year: String = "",
)
