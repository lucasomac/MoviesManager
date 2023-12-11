package br.com.lucolimac.moviesmanager.domain.entity

data class Movie(
    val name: String,
    val releaseYear: Long,
    val producerStudio: String,
    val time: Long,
    val hasWatched: Boolean = false,
    val rating: Long? = null,
    val gender: Gender
)