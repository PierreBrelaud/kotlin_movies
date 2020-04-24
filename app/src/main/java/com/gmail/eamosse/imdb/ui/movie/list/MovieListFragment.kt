package com.gmail.eamosse.imdb.ui.movie.list


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.gmail.eamosse.imdb.databinding.FragmentMovieListBinding
import com.gmail.eamosse.imdb.ui.movie.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MovieListFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var binding: FragmentMovieListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@MovieListFragment
            viewModel = this@MovieListFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {

            getMoviesByGenre(
                MovieListFragmentArgs.fromBundle(
                    arguments!!
                ).genre)

            movies.observe(viewLifecycleOwner, Observer {
                binding.movieList.adapter = MovieAdapter(it) {

                }
            })

            error.observe(viewLifecycleOwner, Observer {
            })
        }
    }
}
