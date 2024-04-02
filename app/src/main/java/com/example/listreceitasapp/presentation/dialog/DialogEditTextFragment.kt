package com.example.listreceitasapp.presentation.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.listreceitasapp.databinding.FragmentDialogEditTextBinding

class DialogEditTextFragment : DialogFragment() {

    private lateinit var binding: FragmentDialogEditTextBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val titleText = arguments?.getString(TITLE_TEXT)
            ?: throw IllegalArgumentException("Ops, passe o titulo")
        val placeHolderText = arguments?.getString(PLACE_HOLDER)
            ?: throw IllegalArgumentException("Ops, passe o place holder")

        return activity?.let {
            binding = FragmentDialogEditTextBinding.inflate(
                requireActivity().layoutInflater
            ).apply {
                edEditText.hint = placeHolderText
                tvTitle.text = titleText
            }

            AlertDialog.Builder(it).setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    setFragmentResult(
                        FRAGMENT_RESULT, bundleOf(
                            EDIT_TEXT_VALUE to binding.edEditText.text.toString()
                        )
                    )
                }
                .setNegativeButton("Cancelar") { _, _ -> dismiss() }
                .create()
        } ?: throw java.lang.IllegalArgumentException("A activity não pode ser null")
    }

    companion object {
        const val TITLE_TEXT = "TITLE_TEXT"
        const val PLACE_HOLDER = "PLACE_HOLDER"

        const val FRAGMENT_RESULT = "FRAGMENT_RESULT"
        const val EDIT_TEXT_VALUE = "EDIT_TEXT_VALUE"

        fun show(
            title: String,
            placeholder: String,
            fragmentManager: FragmentManager,
            tag: String = DialogEditTextFragment::class.java.simpleName.toString(),
        ) {
            DialogEditTextFragment().apply {
                arguments = bundleOf(
                    TITLE_TEXT to title,
                    PLACE_HOLDER to placeholder
                )
            }.show(fragmentManager, tag)
        }
    }
}