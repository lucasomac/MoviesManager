package br.com.lucolimac.moviesmanager.presentation.component

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.lucolimac.moviesmanager.R
import br.com.lucolimac.moviesmanager.databinding.CellMovieBinding
import br.com.lucolimac.moviesmanager.domain.entity.Movie

class MovieAdapter(private val movieOnClickListener: MovieOnClickListener) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(movieDiff) {
    private lateinit var context: Context

    inner class MovieViewHolder(private val binding: CellMovieBinding) : ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                tvNameMovie.text = movie.name
                tvProducerMovie.text = movie.producerStudio
                tvYearDurationMovie.text = movie.getReleaseYearWithDuration()
                checkWasWatchMovie.isChecked = movie.hasWatched
                tvRatinMovie.text = movie.getRatingFormatted()
                btDeleteMovie.setOnClickListener {
                    movieOnClickListener.onDeleteClick(movie)
                }
                checkWasWatchMovie.setOnClickListener {
                    movieOnClickListener.onWatchedClick(movie, it.isActivated)
                }
                root.setOnLongClickListener {
                    val popupMenu = PopupMenu(it.context, it)

                    // Inflating popup menu from popup_menu.xml file
                    popupMenu.menuInflater.inflate(R.menu.action_menu, popupMenu.menu)
                    popupMenu.setOnMenuItemClickListener { menuItem ->
                        when (menuItem.itemId) {
                            R.id.actionChangeMovie -> movieOnClickListener.onUpdateClick(
                                movie
                            )

                            R.id.actionRatingMovie -> movieOnClickListener.onRatingClick(
                                movie, 0
                            )

                        }
                        true
                    }
                    popupMenu.show()
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = CellMovieBinding.inflate(inflater, parent, false)
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