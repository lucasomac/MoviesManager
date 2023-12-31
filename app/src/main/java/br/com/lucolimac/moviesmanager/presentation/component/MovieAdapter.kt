package br.com.lucolimac.moviesmanager.presentation.component

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.lucolimac.moviesmanager.R
import br.com.lucolimac.moviesmanager.databinding.CellMovieBinding
import br.com.lucolimac.moviesmanager.domain.entity.Movie

class MovieAdapter(private val movieOnClickListener: MovieOnClickListener) :
    ListAdapter<Movie, MovieAdapter.MovieViewHolder>(movieDiff), Filterable {
    private lateinit var context: Context
    var originalList: List<Movie> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = CellMovieBinding.inflate(inflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (originalList.isEmpty()) originalList = currentList
                val charSearch = constraint.toString()
                val results = FilterResults().apply {
                    values = if (charSearch.isEmpty() || charSearch.isBlank()) {
                        originalList
                    } else {
                        onFilter(originalList, charSearch)
                    }
                }
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                submitList((results?.values as? List<Movie>)?.toMutableList())
            }
        }
    }

    private fun onFilter(list: List<Movie>, constraint: String): List<Movie> {
        return list.filter {
            it.name.contains(constraint, true)
        }
    }

    inner class MovieViewHolder(private val binding: CellMovieBinding) : ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                tvNameMovie.text = movie.name
                tvProducerMovie.text = movie.producerStudio
                tvYearDurationMovie.text = movie.getReleaseYearWithDuration()
                checkWasWatchMovie.isChecked = movie.hasWatched
                tvRatinMovie.text = movie.getRatingFormatted()
                tvYearGenderMovie.text = movie.gender.toString()
                btDeleteMovie.setOnClickListener {
                    movieOnClickListener.onDeleteClick(movie)
                }
                checkWasWatchMovie.setOnCheckedChangeListener { _, isChecked ->
                    movieOnClickListener.onWatchedClick(movie, isChecked)
                }
                root.setOnClickListener {
                    movieOnClickListener.onClick(movie)
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
                                movie
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