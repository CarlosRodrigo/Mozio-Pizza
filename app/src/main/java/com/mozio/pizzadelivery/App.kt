package com.mozio.pizzadelivery

import android.app.Application
import com.mozio.pizzadelivery.data.di.DataModule
import com.mozio.pizzadelivery.domain.di.DomainModule
import com.mozio.pizzadelivery.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Entrypoint class for the Mozio Pizza Delivery App
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        PresentationModule.load()
        DataModule.load()
        DomainModule.load()
    }
}