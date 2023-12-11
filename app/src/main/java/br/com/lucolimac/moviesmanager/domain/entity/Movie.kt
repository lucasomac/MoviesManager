package br.com.lucolimac.moviesmanager.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val name: String,
    val releaseYear: Long,
    val producerStudio: String,
    val duration: Long,
    val hasWatched: Boolean = false,
    val rating: Long? = null,
    val gender: Gender
) : Parcelable {
    fun getReleaseYearWithDuration() = "$releaseYear - ${duration}min"
    fun getRatingFormatted() = if (rating == null) "N/A" else "$rating/10"
}