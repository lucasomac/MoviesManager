package br.com.lucolimac.moviesmanager.domain.entity

enum class Gender(private val description: String) {
    ROMANCE("Romance"), ADVENTURE("Aventura"), HORROR("Terror"), COMEDY("Comédia");

    override fun toString(): String {
        return description
    }
}