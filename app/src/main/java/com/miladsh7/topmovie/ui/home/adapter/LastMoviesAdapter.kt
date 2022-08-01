package com.miladsh7.topmovie.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.miladsh7.topmovie.databinding.ItemHomeMoviesLastBinding
import com.miladsh7.topmovie.model.home.ResponseMoviesList
import javax.inject.Inject

class LastMoviesAdapter @Inject constructor(

) : RecyclerView.Adapter<LastMoviesAdapter.TopMoviesVh>() {

    private lateinit var binding: ItemHomeMoviesLastBinding
    private var moviesList = emptyList<ResponseMoviesList.Data>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LastMoviesAdapter.TopMoviesVh {
       binding = ItemHomeMoviesLastBinding.inflate(
           LayoutInflater.from(parent.context),parent,false)
        return TopMoviesVh()
    }

    override fun onBindViewHolder(holder: LastMoviesAdapter.TopMoviesVh, position: Int) {
       holder.bindItems(moviesList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = moviesList.size

    inner class TopMoviesVh : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindItems(item: ResponseMoviesList.Data){
            binding.apply {
               moviesNameTxt.text = item.title
                moviesRateTxt.text = item.imdbRating
                moviesCountryTxt.text = item.country
                moviesYearTxt.text = item.year
                moviePosterImg.load(item.poster){
                    crossfade(true)
                    crossfade(800)
                }

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener:((ResponseMoviesList.Data)-> Unit?)? = null

    fun setOnItemClickListener(listener:(ResponseMoviesList.Data)->Unit){
        onItemClickListener = listener
    }

    fun setData(data:List<ResponseMoviesList.Data>){
        val moviesDiffUtil = MoviesDiffUtils(moviesList,data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtil)
        moviesList = data
        diffUtils.dispatchUpdatesTo(this)
    }

    class MoviesDiffUtils(
        private val oldItem:List<ResponseMoviesList.Data>,
        private val newItem:List<ResponseMoviesList.Data>)
        :DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }
    }
}