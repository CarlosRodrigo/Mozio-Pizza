package com.mozio.pizzadelivery.presentation.ui.confirmation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mozio.pizzadelivery.databinding.FragmentConfirmationBinding

/**
 * ConfirmationFragment consolidates the information of an Order
 */
class ConfirmationFragment : Fragment() {

    private val binding: FragmentConfirmationBinding by lazy {
        FragmentConfirmationBinding.inflate(layoutInflater)
    }
    private val args: ConfirmationFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        args.order.flavors?.let { flavors ->
            var flavorsStr = ""
            for (flavor in flavors) {
                flavorsStr += "${flavor.name}\n"
            }
            binding.flavorsDescription.text = flavorsStr
        }
        binding.price.text = "$ ${args.order.price.toString()}"
        return binding.root
    }

}