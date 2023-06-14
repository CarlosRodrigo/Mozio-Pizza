package com.mozio.pizzadelivery

import com.mozio.pizzadelivery.data.repository.PizzaDeliveryRepository
import com.mozio.pizzadelivery.domain.usecases.GetPizzaFlavorsUseCase
import com.mozio.pizzadelivery.domain.usecases.GetPizzaOrderUseCase
import com.mozio.pizzadelivery.mock.PizzaDeliveryRepositoryMock
import org.koin.dsl.module

fun configureDomainModuleForTest() = module {
    factory { GetPizzaOrderUseCase() }
    factory { GetPizzaFlavorsUseCase(get()) }
}

fun configureDataModuleForTest() = module {
    single<PizzaDeliveryRepository> { PizzaDeliveryRepositoryMock() }
}