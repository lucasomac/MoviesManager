package br.com.lucolimac.moviesmanager.domain.entity

import br.com.lucolimac.moviesmanager.R

enum class Gender(
    private val description: String, val icon: Int = R.drawable.round_filter_list_24
) {
    ACTION(
        "Ação", R.drawable.ic_bomb
    ),
    DOCUMENTARY("Documentário", R.drawable.ic_book), FAMILY(
        "Família", R.drawable.ic_people
    ),
    HORROR("Terror", R.drawable.ic_ghost), COMEDY(
        "Comédia"
    ),
    MYSTERY("Mistério", R.drawable.ic_mask), ANIMATION("Animação", R.drawable.ic_comics), ADVENTURE(
        "Aventura"
    ),
    FICTION("Ficção científica", R.drawable.ic_robot), ROMANCE(
        "Romance", R.drawable.ic_heart
    );

    override fun toString(): String {
        return description
    }
}