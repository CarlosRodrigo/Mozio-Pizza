package com.mozio.pizzadelivery.data.remote

import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import retrofit2.http.GET

/**
 * Interface responsible for mozio pizzas API communication using Retrofit
 */
interface PizzaDeliveryAPI {

    @GET("mobile/tests/pizzas.json")
    suspend fun getPizzaFlavors() : List<PizzaFlavor>
}