package br.com.lucolimac.moviesmanager.domain.usecase

import br.com.lucolimac.moviesmanager.domain.entity.Movie

interface MovieUseCase {
    suspend fun createMovie(movie: Movie)

    suspend fun updateMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    suspend fun getAllMovies(): List<Movie>
}