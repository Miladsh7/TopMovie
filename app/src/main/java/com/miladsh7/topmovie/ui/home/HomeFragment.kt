package com.miladsh7.topmovie.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.miladsh7.topmovie.databinding.FragmentHomeBinding
import com.miladsh7.topmovie.ui.home.adapter.GenresAdapter
import com.miladsh7.topmovie.ui.home.adapter.LastMoviesAdapter
import com.miladsh7.topmovie.ui.home.adapter.TopMoviesAdapter
import com.miladsh7.topmovie.utils.initRecycler
import com.miladsh7.topmovie.utils.showInVisible
import com.miladsh7.topmovie.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //Binding
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var topMoviesAdapter: TopMoviesAdapter

    @Inject
    lateinit var genresAdapter: GenresAdapter

    @Inject
    lateinit var lastMoviesAdapter: LastMoviesAdapter

    //other
    private val viewModel: HomeViewModel by viewModels()
    private val pagerHelper: PagerSnapHelper by lazy { PagerSnapHelper() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //call api
        viewModel.loadTopMoviesList(3)
        viewModel.loadGenresList()
        viewModel.loadLastMoviesList()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            //Get top movies
            viewModel.topMoviesList.observe(viewLifecycleOwner) {
                topMoviesAdapter.differ.submitList(it.data)
                //RecyclerView
                topMoviesRecyclerview.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    topMoviesAdapter
                )


                //Indicator
                pagerHelper.attachToRecyclerView(topMoviesRecyclerview)
                topMoviesIndicator.attachToRecyclerView(topMoviesRecyclerview, pagerHelper)
            }
            //Get genres
            viewModel.genresList.observe(viewLifecycleOwner) {
                genresAdapter.differ.submitList(it)
                genresRecycler.initRecycler(
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                    genresAdapter
                )
            }

            //Get last movies
            viewModel.lastMoviesList.observe(viewLifecycleOwner) {
                lastMoviesAdapter.setData(it.data)
                //RecyclerView
                lastMoviesRecycler.initRecycler(
                    LinearLayoutManager(requireContext()),
                    lastMoviesAdapter
                )

            }

            //Click
            lastMoviesAdapter.setOnItemClickListener {

            }

            //Loading
            viewModel.loading.observe(viewLifecycleOwner){
                if (it){
                    moviesLoading.showInVisible(true)
                    moviesScrollLayout.showInVisible(false)
                }else{
                    moviesLoading.showInVisible(false)
                    moviesScrollLayout.showInVisible(true)
                }
            }
        }

    }
}