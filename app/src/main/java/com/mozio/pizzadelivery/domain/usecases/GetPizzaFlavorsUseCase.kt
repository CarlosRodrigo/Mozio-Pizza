package com.mozio.pizzadelivery.domain.usecases

import com.mozio.pizzadelivery.core.UseCase
import com.mozio.pizzadelivery.data.repository.PizzaDeliveryRepository
import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import kotlinx.coroutines.flow.Flow


/**
 * UseCase responsible for connecting to the repository and retrieving the list of pizza flavors
 */
class GetPizzaFlavorsUseCase(private val repository: PizzaDeliveryRepository) :
    UseCase.NoParam<List<PizzaFlavor>>() {

    override suspend fun execute(): Flow<List<PizzaFlavor>> = repository.listPizzaFlavors()
}