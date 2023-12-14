package br.com.lucolimac.moviesmanager.presentation.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.lucolimac.moviesmanager.databinding.GenderHeaderBinding
import br.com.lucolimac.moviesmanager.databinding.GenderItemBinding
import br.com.lucolimac.moviesmanager.domain.entity.Gender


class GenderAdapter(context: Context) : ArrayAdapter<Gender>(
    context, 0, Gender.entries.toTypedArray()
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        val bindingGender = GenderItemBinding.inflate(inflater, parent, false)
        val view: View
        view = bindingGender.root
        getItem(position)?.let { gender ->
            setItemGender(bindingGender, gender, true)
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(parent.context)
        val bindingGender = GenderItemBinding.inflate(inflater, parent, false)
        val view: View
        view = bindingGender.root
        getItem(position)?.let { gender ->
            setItemGender(bindingGender, gender, false)
        }
        return view
    }

    private fun setItemGender(
        genderItemBinding: GenderItemBinding, gender: Gender, isVisibleArrow: Boolean
    ) {
        genderItemBinding.tvGender.text = gender.toString()
        genderItemBinding.ivGender.setBackgroundDrawable(
            ContextCompat.getDrawable(
                context, gender.icon
            )
        )
        genderItemBinding.arrowBackIndicator.isVisible = isVisibleArrow
    }

    private fun setItemHeader(binding: GenderHeaderBinding, isSelected: Boolean) {
        when (isSelected) {
            true -> binding.tvHeader.setCompoundDrawables(
                null,
                null,
                ContextCompat.getDrawable(context, android.R.drawable.arrow_down_float),
                null
            )

            false -> binding.tvHeader.setCompoundDrawables(
                null,
                null,
                ContextCompat.getDrawable(context, android.R.drawable.arrow_up_float),
                null
            )
        }
    }
}