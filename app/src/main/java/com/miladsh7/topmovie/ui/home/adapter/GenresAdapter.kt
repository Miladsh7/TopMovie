package com.miladsh7.topmovie.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.miladsh7.topmovie.databinding.ItemHomeGenreListBinding
import com.miladsh7.topmovie.model.home.ResponseGenreList
import javax.inject.Inject

class GenresAdapter @Inject constructor(

) : RecyclerView.Adapter<GenresAdapter.GenresVh>() {

    private lateinit var binding: ItemHomeGenreListBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenresAdapter.GenresVh {
       binding = ItemHomeGenreListBinding.inflate(
           LayoutInflater.from(parent.context),parent,false)
        return GenresVh()
    }

    override fun onBindViewHolder(holder: GenresAdapter.GenresVh, position: Int) {
       holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class GenresVh : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun setData(item: ResponseGenreList.ResponseGenreListItem){
            binding.apply {

                nameTxt.text = item.name
            }
        }
    }

    private val differCallback = object :DiffUtil.ItemCallback<ResponseGenreList.ResponseGenreListItem>(){
        override fun areItemsTheSame(
            oldItem: ResponseGenreList.ResponseGenreListItem,
            newItem: ResponseGenreList.ResponseGenreListItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ResponseGenreList.ResponseGenreListItem,
            newItem: ResponseGenreList.ResponseGenreListItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

}