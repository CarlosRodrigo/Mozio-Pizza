package com.mozio.pizzadelivery.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mozio.pizzadelivery.core.State
import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import com.mozio.pizzadelivery.domain.model.Order
import com.mozio.pizzadelivery.domain.usecases.GetPizzaFlavorsUseCase
import com.mozio.pizzadelivery.domain.usecases.GetPizzaOrderUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * HomeViewModel used on the HomeFragment
 */
class HomeViewModel(private val getPizzaFlavorsUseCase: GetPizzaFlavorsUseCase,
                    private val getPizzaOrderUseCase: GetPizzaOrderUseCase
) : ViewModel() {

    private val _flavors = MutableLiveData<State<List<PizzaFlavor>>>()
    val flavors: LiveData<State<List<PizzaFlavor>>>
        get() = _flavors

    private val _order = MutableLiveData<State<Order>>()
    val order: LiveData<State<Order>>
        get() = _order

    private val selectedFlavors = mutableListOf<PizzaFlavor>()

    init {
        fetchPizzaFlavors()
    }

    fun addSelectedFlavor(flavor: PizzaFlavor) {
        selectedFlavors.add(flavor)
    }

    fun removeSelectedFlavor(flavor: PizzaFlavor) {
        selectedFlavors.remove(flavor)
    }

    private fun fetchPizzaFlavors() {
        viewModelScope.launch {
            getPizzaFlavorsUseCase()
                .onStart {
                    _flavors.postValue(State.Loading)
                }
                .catch {
                    _flavors.postValue(State.Error(it))
                }
                .collect { flavors ->
                    _flavors.postValue(State.Success(flavors))
                }
        }
    }

    fun checkoutPizza() {
        viewModelScope.launch {
            getPizzaOrderUseCase(selectedFlavors)
                .onStart {
                    _order.postValue(State.Loading)
                }
                .catch {
                    _order.postValue(State.Error(it))
                }
                .collect { order ->
                    _order.postValue(State.Success(order))
                    selectedFlavors.clear()
                }
        }
    }

    fun priceCalculated() {
        _order.postValue(State.Loading)
    }
}