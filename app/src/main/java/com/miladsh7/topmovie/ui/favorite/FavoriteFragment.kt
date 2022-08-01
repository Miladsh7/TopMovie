package com.miladsh7.topmovie.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.miladsh7.topmovie.databinding.FragmentFavoriteBinding
import com.miladsh7.topmovie.ui.favorite.adapter.FavoriteListAdapter
import com.miladsh7.topmovie.utils.initRecycler
import com.miladsh7.topmovie.utils.showInVisible
import com.miladsh7.topmovie.viewModel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    lateinit var favoriteAdapter: FavoriteListAdapter

    //other
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            // show all favorite
            viewModel.loadFavoriteList()
            //list
            viewModel.favoriteList.observe(viewLifecycleOwner) {
                favoriteAdapter.setData(it)
                favoriteRecycler.initRecycler(
                    LinearLayoutManager(requireContext()),
                    favoriteAdapter
                )
            }
            //Empty
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    emptyItemLay.showInVisible(true)
                    favoriteRecycler.showInVisible(false)
                } else {
                    emptyItemLay.showInVisible(false)
                    favoriteRecycler.showInVisible(true)
                }
            }
        }
    }

}