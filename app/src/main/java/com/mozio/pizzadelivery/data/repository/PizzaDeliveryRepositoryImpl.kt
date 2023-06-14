package com.mozio.pizzadelivery.data.repository

import com.mozio.pizzadelivery.core.CustomException
import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import com.mozio.pizzadelivery.data.remote.PizzaDeliveryAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository implementation of listing pizza flavors
 */
class PizzaDeliveryRepositoryImpl(private val service: PizzaDeliveryAPI) : PizzaDeliveryRepository {

    override suspend fun listPizzaFlavors(): Flow<List<PizzaFlavor>> = flow {
        try {
            val flavors = service.getPizzaFlavors()
            emit(flavors)
        } catch (e: Exception) {
            throw CustomException("Unable to connect to the API.")
        }
    }
}