package br.com.lucolimac.moviesmanager.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.lucolimac.moviesmanager.databinding.FragmentRegisterMovieBinding
import br.com.lucolimac.moviesmanager.domain.entity.Gender
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import br.com.lucolimac.moviesmanager.presentation.component.GenderAdapter
import br.com.lucolimac.moviesmanager.presentation.viewmodel.MovieViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.properties.Delegates


class RegisterMovieFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentRegisterMovieBinding? = null
    private val binding get() = _binding!!
    private val args: RegisterMovieFragmentArgs by navArgs()
    private var isEdit by Delegates.notNull<Boolean>()
    private val movieViewModel: MovieViewModel by viewModel<MovieViewModel>()
    private var genderSelected: Gender? = null
    private val genderAdapter: GenderAdapter by inject { parametersOf(requireContext()) }
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
            populateForm(movie)
        }
        setupSpinnerGender()
        setupButtonSave()
    }

    private fun populateForm(movie: Movie) {
        binding.apply {
            tilName.editText?.setText(movie.name)
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

    private fun setupSpinnerGender() {
        binding.spinnerGender.apply {
            onItemSelectedListener = this@RegisterMovieFragment
            adapter = genderAdapter
        }
    }

    private fun setupButtonSave() {
        binding.btnSave.setOnClickListener {
            if (checkAllFields()) {
                val movie = Movie(
                    name = binding.tilName.editText?.text.toString(),
                    releaseYear = binding.tilYear.editText?.text.toString().toLong(),
                    producerStudio = binding.tilProducer.editText?.text.toString(),
                    duration = binding.tilDuration.editText?.text.toString().toLong(),
                    gender = genderSelected!!,
                    hasWatched = binding.checkWatched.isChecked,
                    rating = if (binding.ratingBar.rating != 0.0F) (binding.ratingBar.rating * 2).toInt() else null
                )
                if (isEdit) {
                    movieViewModel.updateMovie(movie)
                    findNavController().navigateUp()
                } else {
                    movieViewModel.createMovie(movie)
                    findNavController().navigateUp()
                }
            } else return@setOnClickListener
        }
    }

    private fun checkAllFields(): Boolean {
        return when {
            binding.tilName.editText?.text.isNullOrBlank() -> {
                binding.tilName.editText?.error = "O nome é obrigatório"
                binding.tilName.editText?.requestFocus()
                false
            }

            binding.tilProducer.editText?.text.isNullOrBlank() -> {
                binding.tilProducer.editText?.error = "A produtora é obrigatório"
                binding.tilProducer.editText?.requestFocus()
                false
            }

            binding.tilYear.editText?.text.isNullOrBlank() -> {
                binding.tilYear.editText?.error = "O ano é obrigatório"
                binding.tilYear.editText?.requestFocus()
                false
            }

            binding.tilDuration.editText?.text.isNullOrBlank() -> {
                binding.tilDuration.editText?.error = "A duração é obrigatória"
                binding.tilDuration.editText?.requestFocus()
                false
            }

            genderSelected == null -> {
                binding.spinnerGender.requestFocus()
                false
            }

            else -> true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        genderSelected = if (position == 0) null else Gender.entries[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        return
    }
}