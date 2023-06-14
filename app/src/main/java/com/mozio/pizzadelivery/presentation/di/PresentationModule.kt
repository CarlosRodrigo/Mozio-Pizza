package com.mozio.pizzadelivery.presentation.di

import com.mozio.pizzadelivery.presentation.ui.home.HomeViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * This class handles the Data dependencies
 */
object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            factory { HomeViewModel(get(), get()) }
        }
    }

}