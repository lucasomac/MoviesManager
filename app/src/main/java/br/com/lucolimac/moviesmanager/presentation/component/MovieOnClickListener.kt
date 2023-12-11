package br.com.lucolimac.moviesmanager.presentation.component

import br.com.lucolimac.moviesmanager.domain.entity.Movie

interface MovieOnClickListener {
    fun onEraseClick(movie: Movie)
    fun onRatingClick(movie: Movie, rating: Int)
    fun onWatchedClick(movie: Movie)
    fun onClick(movie: Movie)
    fun onLongClick(movie: Movie)
}