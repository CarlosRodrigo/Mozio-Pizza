package com.mozio.pizzadelivery.data.repository

import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for listing pizza flavors
 */
interface PizzaDeliveryRepository {

    suspend fun listPizzaFlavors() : Flow<List<PizzaFlavor>>
}