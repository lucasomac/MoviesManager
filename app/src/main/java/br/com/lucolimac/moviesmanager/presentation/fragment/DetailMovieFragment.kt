package br.com.lucolimac.moviesmanager.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.lucolimac.moviesmanager.databinding.FragmentDetailMovieBinding

class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    private val args: DetailMovieFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = args.movie
        binding.apply {
            tvNameMovie.text = movie.name
            tvProducerMovie.text = movie.producerStudio
            tvYearDurationMovie.text = movie.getReleaseYearWithDuration()
            tvRatingMovie.text = movie.getRatingFormatted()
            tvGenderMovie.text = movie.gender.toString()
            movie.rating?.let {
                ratingBar.rating = it.toFloat() / 2.0F
            }
            checkWasWatchMovie.isChecked = movie.hasWatched
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}