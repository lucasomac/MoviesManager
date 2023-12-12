package br.com.lucolimac.moviesmanager.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucolimac.moviesmanager.domain.entity.Gender
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import br.com.lucolimac.moviesmanager.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieUseCase: MovieUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    private val _listOfMovies = MutableLiveData<List<Movie>>()
    val listOfMovies: LiveData<List<Movie>> = _listOfMovies
    fun getAllMovies() {
        viewModelScope.launch(dispatcher) {
            _listOfMovies.postValue(movieUseCase.getAllMovies())
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