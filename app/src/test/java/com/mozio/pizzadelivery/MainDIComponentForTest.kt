package com.mozio.pizzadelivery

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

fun configureTestAppComponent() = startKoin {
    loadKoinModules(
        listOf(
            configureDomainModuleForTest(),
            configureDataModuleForTest()
        )
    )
}