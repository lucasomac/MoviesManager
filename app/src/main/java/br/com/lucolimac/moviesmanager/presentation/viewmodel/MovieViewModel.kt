package br.com.lucolimac.moviesmanager.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucolimac.moviesmanager.domain.entity.Gender
import br.com.lucolimac.moviesmanager.domain.entity.Movie

class MovieViewModel : ViewModel() {
    private val _listOfMovies = MutableLiveData(
        listOf(
            Movie("Vingadores", 2023, "Marvel", 345L, Gender.ADVENTURE),
            Movie("Tico e Teco", 2020, "Disney", 125L, Gender.COMEDY)
        )
    )
    val listOfMovies: LiveData<List<Movie>> = _listOfMovies

}