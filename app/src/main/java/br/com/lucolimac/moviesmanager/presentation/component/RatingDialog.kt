package br.com.lucolimac.moviesmanager.presentation.component

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import br.com.lucolimac.moviesmanager.databinding.RatingDialogBinding

class RatingDialog(private val context: Context) : Dialog(context) {
    private val binding: RatingDialogBinding by lazy {
        RatingDialogBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}