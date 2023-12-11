package br.com.lucolimac.moviesmanager.presentation.component

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.lucolimac.moviesmanager.databinding.CellMoviewBinding
import br.com.lucolimac.moviesmanager.domain.entity.Movie

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(movieDiff) {
    private lateinit var context: Context

    inner class MovieViewHolder(private val binding: CellMoviewBinding) : ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                tvNameMovie.text = movie.name
                tvProducerMovie.text = movie.producerStudio
                tvYearDurationMovie.text = movie.getReleaseYearWithDuration()
                checkWasWatchMovie.isChecked = movie.hasWatched
                tvRatinMovie.text = movie.getRatingFormatted()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = CellMoviewBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val movieDiff = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.name == newItem.name
            }

        }
    }
}