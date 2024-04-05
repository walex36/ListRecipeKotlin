package com.example.listreceitasapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.listreceitasapp.R
import com.example.listreceitasapp.data.entity.PrepareMode
import com.example.listreceitasapp.databinding.FragmentDetailBinding
import com.example.listreceitasapp.presentation.detail.adapter.IngredientAdapter
import com.example.listreceitasapp.presentation.detail.adapter.PrepareModeAdapter
import com.example.listreceitasapp.presentation.dialog.DialogEditTextFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import presentation.detail.DetailFragmentArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val args by navArgs<DetailFragmentArgs>()

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModel.Factory(args.idRecipe)
    }

    private val adapterIngredients by lazy { IngredientAdapter() }
    private val adapterPrepareMode by lazy { PrepareModeAdapter() }

    private var typeDialog = ""
    private var idIngredientOrPrepareModeUpdate = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observe()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnAddIngredient.setOnClickListener {
            showDialogIngredient(true, "")
        }
        binding.btnAddPrepareMode.setOnClickListener {
            showDialogPrepareMode(true, "")
        }

        setFragmentResultListener(DialogEditTextFragment.FRAGMENT_RESULT) { _, bundle ->
            val name = bundle.getString(DialogEditTextFragment.EDIT_TEXT_VALUE) ?: ""
            if (idIngredientOrPrepareModeUpdate == 0) {
                viewModel.insertIngredientsOrPrepareMode(
                    typeInsert = typeDialog, name = name, idRecipe = args.idRecipe
                )
            } else {
                viewModel.updateIngredientsOrPrepareMode(
                    typeUpdate = typeDialog,
                    id = idIngredientOrPrepareModeUpdate,
                    name = name,
                    idRecipe = args.idRecipe
                )
            }
        }
        adapterIngredients.edit = {
            idIngredientOrPrepareModeUpdate = it.id
            showDialogIngredient(false, it.name)
        }
        adapterIngredients.remove = {
            idIngredientOrPrepareModeUpdate = 0
            typeDialog = "INGREDIENT"
            viewModel.removeIngredientsOrPrepareMode(typeDelete = typeDialog, id = it.id)
        }

        adapterPrepareMode.edit = {
            idIngredientOrPrepareModeUpdate = it.id
            showDialogPrepareMode(false, it.name)
        }

        adapterPrepareMode.remove = {
            idIngredientOrPrepareModeUpdate = 0
            typeDialog = "PREPARE_MODE"
            viewModel.removeIngredientsOrPrepareMode(typeDelete = typeDialog, id = it.id)
        }
    }

    private fun observe() {
        viewModel.state.observe(viewLifecycleOwner) {
                when (it) {
                    ItemListState.Loading -> {
                        binding.ctIngredientsAndPrepareMode.isVisible = false
                        binding.pbLoading.isVisible = true
                    }

                    is ItemListState.Error -> {
                        binding.pbLoading.isVisible = false
                        binding.ctIngredientsAndPrepareMode.isVisible = true
                        Toast.makeText(
                            requireContext(),
                            "Erro ao buscar os ingredientes e Modos de preparo",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is ItemListState.Success -> {
                        binding.pbLoading.isVisible = false
                        binding.ctIngredientsAndPrepareMode.isVisible = true
                        adapterIngredients.submitList(it.ingredients)
                        adapterPrepareMode.submitList(it.prepareMode)
                    }
                }
            }
    }

    private fun setupAdapter() {
        binding.rvIngredients.adapter = adapterIngredients
        binding.rvPrepareMode.adapter = adapterPrepareMode
    }

    private fun showDialogIngredient(isInsert: Boolean, nameIngredient: String) {
        typeDialog = "INGREDIENT"
        if (isInsert) {
            DialogEditTextFragment.show(
                title = getString(R.string.label_new_ingredient),
                placeholder = getString(R.string.label_item_description),
                valueEditText = "",
                fragmentManager = parentFragmentManager
            )
        } else {
            DialogEditTextFragment.show(
                title = getString(R.string.label_new_ingredient),
                placeholder = getString(R.string.label_item_description),
                valueEditText = nameIngredient,
                fragmentManager = parentFragmentManager
            )
        }
    }

    private fun showDialogPrepareMode(isInsert: Boolean, namePrepareMode: String) {
        typeDialog = "PREPARE_MODE"
        if (isInsert){
        DialogEditTextFragment.show(
            title = getString(R.string.label_new_prepare_mode),
            placeholder = getString(R.string.label_item_description),
            valueEditText = "",
            fragmentManager = parentFragmentManager
        )
        }else{
            DialogEditTextFragment.show(
                title = getString(R.string.label_new_prepare_mode),
                placeholder = getString(R.string.label_item_description),
                valueEditText = namePrepareMode,
                fragmentManager = parentFragmentManager
            )
        }
    }

    private fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            owner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@observe.collect(observe)
            }
        }
    }
}
