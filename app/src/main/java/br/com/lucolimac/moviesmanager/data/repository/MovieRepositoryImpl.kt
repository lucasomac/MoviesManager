package br.com.lucolimac.moviesmanager.data.repository

import br.com.lucolimac.moviesmanager.data.datasource.MovieDao
import br.com.lucolimac.moviesmanager.data.model.MovieModel
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import br.com.lucolimac.moviesmanager.domain.usecase.MovieUseCase

class MovieRepositoryImpl(private val movieDao: MovieDao) : MovieUseCase {
    override suspend fun createMovie(movie: Movie) {
        movieDao.createMovie(MovieModel.fromEntity(movie))
    }

    override suspend fun updateMovie(movie: Movie) {
        movieDao.updateMovie(MovieModel.fromEntity(movie))
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(MovieModel.fromEntity(movie))
    }

    override suspend fun getAllMoviesByName(): List<Movie> {
        return movieDao.getAllMoviesByName().map { it.toEntity() }
    }

    override suspend fun getAllMoviesByRating(): List<Movie> {
        return movieDao.getAllMoviesByRating().map { it.toEntity() }
    }
}