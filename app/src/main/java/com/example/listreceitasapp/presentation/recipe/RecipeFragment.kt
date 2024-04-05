package com.example.listreceitasapp.presentation.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.listreceitasapp.R
import com.example.listreceitasapp.databinding.FragmentRecipeBinding
import com.example.listreceitasapp.presentation.dialog.DialogEditTextFragment
import com.example.listreceitasapp.presentation.recipe.adapter.RecipeAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import presentation.recipe.RecipeFragmentDirections

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecipeFragment : Fragment() {

    private val viewModel: RecipeViewModel by viewModels {
        RecipeViewModel.Factory()
    }
    private lateinit var binding: FragmentRecipeBinding
    private val adapter by lazy { RecipeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpAdapter()
        observeStates()
    }

    private fun setUpListeners() {
        binding.fabRecipe.setOnClickListener {
            showDialog()
        }
        setFragmentResultListener(DialogEditTextFragment.FRAGMENT_RESULT) {  requestKey, bundle ->
            val nomeRecipe = bundle.getString(DialogEditTextFragment.EDIT_TEXT_VALUE) ?: ""
            viewModel.insert(nomeRecipe)
        }

        adapter.click = { recipeItem ->
            val action = RecipeFragmentDirections.actionFirstFragmentToSecondFragment(
                recipeItem.id,
            )
            findNavController().navigate(action)
        }
    }

    private fun setUpAdapter() {
        binding.rvRecipes.adapter = adapter
    }

    private fun observeStates() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                RecipeState.Empty -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.toast_recipe_empty), Toast.LENGTH_LONG
                    ).show()
                }

                RecipeState.Loading -> {
                    binding.pbLoading.isVisible = true
                }

                is RecipeState.Error -> {
                    binding.pbLoading.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        state.message, Toast.LENGTH_LONG
                    ).show()
                }

                is RecipeState.Success -> {
                    binding.pbLoading.isVisible = false
                    adapter.submitList(state.recipes)
                }
            }

        }
    }

    private fun showDialog() {
        DialogEditTextFragment.show(
            title = getString(R.string.title_new_recipe),
            placeholder = getString(R.string.label_name_recipe),
            valueEditText = "",
            fragmentManager = parentFragmentManager,
        )
    }

        private fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
            owner.lifecycleScope.launch {
                owner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    this@observe.collect(observe)
                }
            }
        }
}