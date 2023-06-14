package com.mozio.pizzadelivery.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mozio.pizzadelivery.core.State
import com.mozio.pizzadelivery.databinding.FragmentHomeBinding
import com.mozio.pizzadelivery.presentation.adapter.FlavorAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * HomeFragment is the start destination and main app fragment
 */
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        FlavorAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding()
        initRecyclerView()
        observeFlavors()
        observePizzaPrice()
        return binding.root
    }

    private fun initBinding() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.confirmBtn.setOnClickListener {
            viewModel.checkoutPizza()
        }
    }

    private fun initRecyclerView() {
        adapter.onFlavorAddedListener = { flavor ->
            viewModel.addSelectedFlavor(flavor)
        }
        adapter.onFlavorRemovedListener = { flavor ->
            viewModel.removeSelectedFlavor(flavor)
        }
        binding.flavorsRv.adapter = adapter
        binding.flavorsRv.layoutManager = LinearLayoutManager(context)
    }

    private fun observeFlavors() {
        viewModel.flavors.observe(viewLifecycleOwner) { state ->
            when (state) {
                State.Loading -> {
                    // Show some progressbar or loading screen.
                    // Not implementing for this assignment but that's how it could be done using
                    // this architecture.
                }
                is State.Error -> {
                    Snackbar.make(binding.root, state.error.localizedMessage, Snackbar.LENGTH_LONG).show()
                }
                is State.Success -> {
                    adapter.updateFlavors(state.result.toMutableList())
                }
            }
        }
    }

    private fun observePizzaPrice() {
        viewModel.order.observe(viewLifecycleOwner) { state ->
            when (state) {
                State.Loading -> { }
                is State.Error -> {
                    Snackbar.make(binding.root, state.error.localizedMessage, Snackbar.LENGTH_LONG).show()
                }
                is State.Success -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToConfirmationFragment(state.result)
                    findNavController().navigate(action)
                    viewModel.priceCalculated()
                }
            }
        }
    }

}