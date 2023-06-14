package com.mozio.pizzadelivery.data.di

import android.util.Log
import com.mozio.pizzadelivery.data.remote.PizzaDeliveryAPI
import com.mozio.pizzadelivery.data.repository.PizzaDeliveryRepository
import com.mozio.pizzadelivery.data.repository.PizzaDeliveryRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This class handles the Data dependencies
 */
object DataModule {

    private const val BASE_URL = "https://static.mozio.com/"
    private const val OK_HTTP = "OK_HTTP"

    fun load() {
        loadKoinModules(
            listOf(
                pizzaFlavorsModule(),
                networkModule()
            )
        )
    }

    private fun pizzaFlavorsModule() : Module {
        return module {
            single<PizzaDeliveryRepository> { PizzaDeliveryRepositoryImpl(get()) }
        }
    }

    private fun networkModule(): Module {
        return module {
            single<PizzaDeliveryAPI> { createAPI(get(), get()) }

            single { Moshi.Builder().add((KotlinJsonAdapterFactory())).build() }

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.d(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }
        }
    }

    private inline fun <reified T> createAPI(
        factory: Moshi,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
            .create(T::class.java)
    }
}