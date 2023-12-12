package br.com.lucolimac.moviesmanager.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import br.com.lucolimac.moviesmanager.databinding.FragmentRegisterMovieBinding
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import kotlin.properties.Delegates

class RegisterMovieFragment : Fragment() {
    private var _binding: FragmentRegisterMovieBinding? = null
    private val binding get() = _binding!!
    private val args: RegisterMovieFragmentArgs by navArgs()
    private var isEdit by Delegates.notNull<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isEdit = args.movie != null
        val movie = args.movie
        if (movie != null) {
            setupView(movie)
        }
    }

    private fun setupView(movie: Movie) {
        if (isEdit) {
            binding.apply {
                tilName.isEnabled = false
                tilProducer.editText?.setText(movie.producerStudio)
                tilYear.editText?.setText(movie.releaseYear.toString())
                tilDuration.editText?.setText(movie.duration.toString())
                checkWatched.isChecked = movie.hasWatched
                movie.rating?.let {
                    ratingBar.rating = it.toFloat() / 2.0F
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}