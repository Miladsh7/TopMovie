package com.miladsh7.topmovie.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.miladsh7.topmovie.databinding.ItemHomeMoviesTopBinding
import com.miladsh7.topmovie.model.home.ResponseMoviesList
import javax.inject.Inject

class TopMoviesAdapter @Inject constructor(

) : RecyclerView.Adapter<TopMoviesAdapter.TopMoviesVh>() {

    private lateinit var binding: ItemHomeMoviesTopBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopMoviesAdapter.TopMoviesVh {
       binding = ItemHomeMoviesTopBinding.inflate(
           LayoutInflater.from(parent.context),parent,false)
        return TopMoviesVh()
    }

    override fun onBindViewHolder(holder: TopMoviesAdapter.TopMoviesVh, position: Int) {
       holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = if (differ.currentList.size>5) 5 else differ.currentList.size

    inner class TopMoviesVh : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseMoviesList.Data){
            binding.apply {
                movieNameTxt.text = item.title
                movieInfoTxt.text = "${item.imdbRating} | ${item.country} | ${item.year}"
                moviePosterImg.load(item.poster){
                    crossfade(true)
                    crossfade(800)
                }
            }
        }
    }

    private val differCallback = object :DiffUtil.ItemCallback<ResponseMoviesList.Data>(){
        override fun areItemsTheSame(
            oldItem: ResponseMoviesList.Data,
            newItem: ResponseMoviesList.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseMoviesList.Data,
            newItem: ResponseMoviesList.Data
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

}