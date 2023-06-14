package com.mozio.pizzadelivery.domain.di

import com.mozio.pizzadelivery.domain.usecases.GetPizzaFlavorsUseCase
import com.mozio.pizzadelivery.domain.usecases.GetPizzaOrderUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * This class handles the Domain dependencies
 */
object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory { GetPizzaOrderUseCase() }
            factory { GetPizzaFlavorsUseCase(get()) }
        }
    }
}