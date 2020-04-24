package com.gmail.eamosse.imdb.ui.movie.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gmail.eamosse.imdb.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {
    private  val viewModel: MovieDetailViewModel by viewModel()
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@MovieDetailFragment
            viewModel = this@MovieDetailFragment.viewModel

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel){
            getMovie(MovieDetailFragmentArgs.fromBundle(arguments!!).id)
            movie.observe(viewLifecycleOwner, Observer {
                binding.item = it
            })
        }
    }
}