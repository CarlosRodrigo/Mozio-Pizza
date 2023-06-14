package com.mozio.pizzadelivery.domain.usecases

import com.mozio.pizzadelivery.core.CustomException
import com.mozio.pizzadelivery.core.UseCase
import com.mozio.pizzadelivery.domain.model.Order
import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * UseCase responsible for calculating the final price based on the selected flavors
 * and creating the order
 */
class GetPizzaOrderUseCase: UseCase<List<PizzaFlavor>, Order>() {
    override suspend fun execute(param: List<PizzaFlavor>): Flow<Order> = flow {
        if (param.isEmpty()) {
            throw CustomException("Please select at least one type of flavor.")
        }
        if (param.size > 2) {
            throw CustomException("You can only choose two types of flavors.")
        }
        var price = 0.0
        if (param.size == 1) {
            price = param[0].price
        }
        if (param.size == 2) {
            price = param[0].price / 2 + param[1].price / 2
        }

        val order = Order(param.toMutableList(), price)
        emit(order)
    }
}