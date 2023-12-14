package br.com.lucolimac.moviesmanager.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import br.com.lucolimac.moviesmanager.domain.entity.Sort
import br.com.lucolimac.moviesmanager.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    private val _listOfMovies = MutableStateFlow<List<Movie>>(listOf())
    val listOfMovies: StateFlow<List<Movie>> = _listOfMovies.asStateFlow()
    fun getAllMovies(sort: Sort = Sort.NAME) {
        viewModelScope.launch(dispatcher) {
            when (sort) {
                Sort.NAME -> _listOfMovies.value = movieUseCase.getAllMoviesByName()
                Sort.RATING -> _listOfMovies.value = movieUseCase.getAllMoviesByRating()
            }
        }
    }

    fun createMovie(movie: Movie) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.createMovie(movie)
            getAllMovies()
        }
    }

    fun updateMovie(movie: Movie) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.updateMovie(movie)
            getAllMovies()
        }
    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.deleteMovie(movie)
            getAllMovies()
        }
    }

    fun ratingMovie(movie: Movie, rating: Int) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.updateMovie(movie.copy(rating = rating))
            getAllMovies()
        }
    }

    fun watchMovie(movie: Movie, hasWatched: Boolean) {
        viewModelScope.launch(dispatcher) {
            movieUseCase.updateMovie(movie.copy(hasWatched = hasWatched))
            getAllMovies()
        }
    }
}