package com.miladsh7.topmovie.model.home


import com.google.gson.annotations.SerializedName

class ResponseGenreList : ArrayList<ResponseGenreList.ResponseGenreListItem>() {
    data class ResponseGenreListItem(
        @SerializedName("id")
        val id: Int?, // 1
        @SerializedName("name")
        val name: String? // Sport
    )
}