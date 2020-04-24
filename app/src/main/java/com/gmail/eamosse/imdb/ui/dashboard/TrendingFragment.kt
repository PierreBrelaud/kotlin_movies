package com.gmail.eamosse.imdb.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import com.gmail.eamosse.imdb.databinding.TrendingFragmentBinding
import com.gmail.eamosse.imdb.ui.dashboard.adapters.TrendingCategoryAdapter
import com.gmail.eamosse.imdb.ui.dashboard.adapters.TrendingMovieAdapter
import com.gmail.eamosse.imdb.ui.dashboard.adapters.TrendingPersonAdapter
import kotlinx.android.synthetic.main.trending_fragment.*
import kotlinx.android.synthetic.main.trending_movie_item.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrendingFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModel()
    private lateinit var binding: TrendingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TrendingFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@TrendingFragment
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(trendingViewModel) {
            trendingPeople.observe(viewLifecycleOwner, Observer {
                binding.trendingPeople.categoryList.adapter = TrendingPersonAdapter(it)
                binding.trendingPeople.shimmerViewContainer.hideShimmer()
            })

            trendingMovies.observe(viewLifecycleOwner, Observer {
                binding.trendingMovies.categoryList.adapter = TrendingMovieAdapter(it)
                binding.trendingMovies.shimmerViewContainer.hideShimmer()
            })

            topCategories.observe(viewLifecycleOwner, Observer {
                binding.trendingCategories.categoryList.adapter = TrendingCategoryAdapter(it)
                binding.trendingCategories.shimmerViewContainer.hideShimmer()
            })
        }
    }
}
