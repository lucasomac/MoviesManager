package br.com.lucolimac.moviesmanager.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.lucolimac.moviesmanager.R
import br.com.lucolimac.moviesmanager.databinding.FragmentListMovieBinding
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import br.com.lucolimac.moviesmanager.presentation.component.MovieAdapter
import br.com.lucolimac.moviesmanager.presentation.component.MovieOnClickListener
import br.com.lucolimac.moviesmanager.presentation.component.Separator
import br.com.lucolimac.moviesmanager.presentation.viewmodel.MovieViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ListMovieFragment : Fragment(), MovieOnClickListener {

    private var _binding: FragmentListMovieBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModel<MovieViewModel>()
    private val movieAdapter: MovieAdapter by inject()
    private val separator: Separator by inject { parametersOf(16) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        movieViewModel.listOfMovies.observe(requireActivity()) {
            movieAdapter.submitList(it)
            binding.recyclerListMovie.adapter = movieAdapter
            binding.recyclerListMovie.addItemDecoration(separator)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDeleteClick(movie: Movie) {
        movieViewModel.deleteMovie(movie)
    }

    override fun onUpdateClick(movie: Movie) {
        movieViewModel.updateMovie(movie)
    }

    override fun onRatingClick(movie: Movie, rating: Int) {
        movieViewModel.ratingMovie(movie, rating)
    }

    override fun onWatchedClick(movie: Movie, hasWatched: Boolean) {
        movieViewModel.watchMovie(movie, hasWatched)
    }

    override fun onClick(movie: Movie) {
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}