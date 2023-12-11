package br.com.lucolimac.moviesmanager.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.lucolimac.moviesmanager.databinding.FragmentListMovieBinding
import br.com.lucolimac.moviesmanager.domain.entity.Gender
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import br.com.lucolimac.moviesmanager.presentation.component.MovieAdapter
import br.com.lucolimac.moviesmanager.presentation.component.Separator

class ListMovieFragment : Fragment() {

    private var _binding: FragmentListMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListMovieBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MovieAdapter().apply {
            submitList(
                listOf(
                    Movie("Vingadores", 2023, "Marvel", 345L, Gender.ADVENTURE),
                    Movie("Tico e Teco", 2020, "Disney", 125L, Gender.COMEDY)
                )
            )
        }
        binding.recyclerListMovie.adapter = adapter
        binding.recyclerListMovie.addItemDecoration(Separator(16))
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}