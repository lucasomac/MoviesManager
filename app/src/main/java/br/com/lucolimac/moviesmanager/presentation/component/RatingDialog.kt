package br.com.lucolimac.moviesmanager.presentation.component

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import br.com.lucolimac.moviesmanager.databinding.RatingDialogBinding
import br.com.lucolimac.moviesmanager.domain.entity.Movie

class RatingDialog(
    context: Context,
    private val movie: Movie,
    private val sendRatingValue: (Movie, Int) -> Unit
) : Dialog(context) {
    private val binding: RatingDialogBinding by lazy {
        RatingDialogBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupButtons()
    }

    private fun setupButtons() {
        binding.apply {
            btCancel.setOnClickListener { dismiss() }
            btSend.setOnClickListener {
                sendRatingValue(movie, (ratingBar.rating * 2).toInt())
                dismiss()
            }
        }
    }
}