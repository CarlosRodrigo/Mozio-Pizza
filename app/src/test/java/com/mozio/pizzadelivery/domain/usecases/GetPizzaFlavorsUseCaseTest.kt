package com.mozio.pizzadelivery.domain.usecases

import com.mozio.pizzadelivery.configureTestAppComponent
import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class GetPizzaFlavorsUseCaseTest : KoinTest {

    private val getPizzaFlavorsUseCase: GetPizzaFlavorsUseCase by inject()

    companion object {

        @BeforeClass
        @JvmStatic
        fun setup() {
            configureTestAppComponent()
        }

        /**
         * Stop Koin after each test to prevent errors
         */
        @AfterClass
        fun tearDown() {
            stopKoin()
        }
    }

    @Test
    fun should_ReturnFlavorList_WhenConnectingToRepository() {
        runBlocking {
            val result = getPizzaFlavorsUseCase()

            assertNotNull(result)
            result.collect {
                assertTrue(it is List<PizzaFlavor>)
            }
        }
    }
}