package br.com.lucolimac.moviesmanager.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.lucolimac.moviesmanager.databinding.BottomSheetSortMoviesBinding
import br.com.lucolimac.moviesmanager.domain.entity.Sort
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip

class SortBottomSheet(val onChangeSortSelection: (Sort) -> Unit) : BottomSheetDialogFragment() {
    private var _binding: BottomSheetSortMoviesBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetSortMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChipGroup()
    }

    private fun setupChipGroup() {
        binding.chipGroupSort.setOnCheckedStateChangeListener { group, checkedIds ->
            val chip: Chip = group.findViewById(checkedIds[0])
            chip.let { chipView ->
                if (chipView.text == Sort.NAME.description) {
                    onChangeSortSelection(Sort.NAME)
                } else {
                    onChangeSortSelection(Sort.RATING)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}