package com.mozio.pizzadelivery.mock

import com.mozio.pizzadelivery.data.repository.PizzaDeliveryRepository
import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PizzaDeliveryRepositoryMock : PizzaDeliveryRepository {
    override suspend fun listPizzaFlavors(): Flow<List<PizzaFlavor>> = flow {
        val flavors = mutableListOf(
            PizzaFlavor("Mozzarella", 10.0),
            PizzaFlavor("Pepperoni", 12.0),
            PizzaFlavor("Vegetarian", 9.5),
            PizzaFlavor("Super Cheese", 11.0)
        )
        emit(flavors)
    }
}