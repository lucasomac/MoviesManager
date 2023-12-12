package br.com.lucolimac.moviesmanager.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.lucolimac.moviesmanager.databinding.FragmentRegisterMovieBinding
import br.com.lucolimac.moviesmanager.domain.entity.Gender
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import br.com.lucolimac.moviesmanager.presentation.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates
import android.R


class RegisterMovieFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private var _binding: FragmentRegisterMovieBinding? = null
    private val binding get() = _binding!!
    private val args: RegisterMovieFragmentArgs by navArgs()
    private var isEdit by Delegates.notNull<Boolean>()
    private val movieViewModel: MovieViewModel by viewModel<MovieViewModel>()
    private var isAllFieldsChecked: MutableLiveData<Boolean> = MutableLiveData(false)
    private lateinit var genderSelected: Gender
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
        val spinnerAdapter: ArrayAdapter<Gender> = ArrayAdapter<Gender>(
            requireContext(), R.layout.simple_spinner_item, Gender.entries.toTypedArray()
        )
        spinnerAdapter.setDropDownViewResource(
            R.layout.simple_spinner_dropdown_item
        )
        binding.spinnerGender.apply {
            onItemSelectedListener = this@RegisterMovieFragment
            adapter = spinnerAdapter
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
                    gender = genderSelected,
                    hasWatched = binding.checkWatched.isChecked,
                    rating = if (binding.ratingBar.rating != 0.0F) (binding.ratingBar.rating * 2).toInt() else null
                )
                if (isEdit) {
                    movieViewModel.updateMovie(movie)
                    findNavController().popBackStack()
                } else {
                    movieViewModel.createMovie(movie)
                    findNavController().popBackStack()
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

            else -> true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        genderSelected = Gender.entries.toTypedArray()[position]
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}