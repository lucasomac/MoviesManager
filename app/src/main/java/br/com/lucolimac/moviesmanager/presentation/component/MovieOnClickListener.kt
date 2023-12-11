package br.com.lucolimac.moviesmanager.presentation.component

import br.com.lucolimac.moviesmanager.domain.entity.Movie

interface MovieOnClickListener {
    fun onClick(movie: Movie)
    fun onDeleteClick(movie: Movie)
    fun onUpdateClick(movie: Movie)
    fun onRatingClick(movie: Movie, rating: Int)
    fun onWatchedClick(movie: Movie, hasWatched: Boolean)
}