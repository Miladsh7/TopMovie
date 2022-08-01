package com.miladsh7.topmovie.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.miladsh7.topmovie.databinding.FragmentSearchBinding
import com.miladsh7.topmovie.ui.home.adapter.LastMoviesAdapter
import com.miladsh7.topmovie.utils.initRecycler
import com.miladsh7.topmovie.utils.showInVisible
import com.miladsh7.topmovie.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchAdapter: LastMoviesAdapter

    //other
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding.apply {
            //Search
            searchEdt.addTextChangedListener {
                val search = it.toString()
                if (search.isNotEmpty()) {
                    viewModel.loadSearchMovie(search)
                }
            }
            //Get movies list
            viewModel.moviesList.observe(viewLifecycleOwner) {
                searchAdapter.setData(it.data)
                moviesRecycler.initRecycler(LinearLayoutManager(requireContext()), searchAdapter)
            }

            //Loading
            viewModel.loading.observe(viewLifecycleOwner) {
                if (it) {
                    searchLoading.showInVisible(true)
                } else {
                    searchLoading.showInVisible(false)
                }
            }
            //Empty items
            viewModel.empty.observe(viewLifecycleOwner) {
                if (it) {
                    emptyItemLay.showInVisible(true)
                    moviesRecycler.showInVisible(false)
                } else {
                    emptyItemLay.showInVisible(false)
                    moviesRecycler.showInVisible(true)
                }
            }
        }
    }
}