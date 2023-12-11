package br.com.lucolimac.moviesmanager.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.lucolimac.moviesmanager.domain.entity.Gender
import br.com.lucolimac.moviesmanager.domain.entity.Movie
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
class MovieModel(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val releaseYear: Long,
    val producerStudio: String,
    val duration: Long,
    val gender: String,
    val hasWatched: Boolean,
    val rating: Long?
) : Parcelable {
    fun toEntity(): Movie {
        return Movie(
            id,
            name,
            releaseYear,
            producerStudio,
            duration,
            Gender.valueOf(gender),
            hasWatched,
            rating
        )
    }

    companion object {
        fun fromEntity(movie: Movie): MovieModel {
            return MovieModel(
                movie.id,
                movie.name,
                movie.releaseYear,
                movie.producerStudio,
                movie.duration,
                movie.gender.description,
                movie.hasWatched,
                movie.rating
            )
        }
    }
}