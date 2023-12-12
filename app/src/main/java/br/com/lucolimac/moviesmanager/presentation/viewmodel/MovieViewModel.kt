package br.com.lucolimac.moviesmanager.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucolimac.moviesmanager.domain.entity.Gender
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import br.com.lucolimac.moviesmanager.domain.usecase.MovieUseCase

class MovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {
    private val _listOfMovies = MutableLiveData(
        listOf(
            Movie(1, "Vingadores", 2023, "Marvel", 345, Gender.ADVENTURE),
            Movie(2, "Tico e Teco", 2020, "Disney", 125, Gender.COMEDY),
            Movie(3, "Mulher Maravilha", 2020, "Disney", 125, Gender.ADVENTURE, rating = 10)
        )
    )
    val listOfMovies: LiveData<List<Movie>> = _listOfMovies

    fun createMovie(movie: Movie) {

    }

    fun updateMovie(movie: Movie) {

    }

    fun deleteMovie(movie: Movie) {

    }

    fun ratingMovie(movie: Movie, rating: Int) {

    }

    fun watchMovie(movie: Movie, hasWatched: Boolean) {

    }
}