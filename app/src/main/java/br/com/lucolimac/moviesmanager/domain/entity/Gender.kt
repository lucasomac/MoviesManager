package br.com.lucolimac.moviesmanager.domain.entity

enum class Gender(private val description: String) {
    ADVENTURE("Aventura"), ROMANCE("Romance"), HORROR("Terror"), COMEDY("Comédia");

    override fun toString(): String {
        return description
    }
}