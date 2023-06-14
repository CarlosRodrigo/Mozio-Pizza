package com.mozio.pizzadelivery.domain.usecases

import com.mozio.pizzadelivery.configureTestAppComponent
import com.mozio.pizzadelivery.core.CustomException
import com.mozio.pizzadelivery.domain.model.PizzaFlavor
import kotlinx.coroutines.runBlocking
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

internal class GetPizzaOrderUseCaseTest : KoinTest{

    private val getPizzaOrderUseCase: GetPizzaOrderUseCase by inject()

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

    @Test(expected = CustomException::class)
    fun should_ReturnError_WhenListOfFlavorsIsEmpty() {
        runBlocking {
            val result = getPizzaOrderUseCase(emptyList())
            result.collect { }
        }
    }


    @Test(expected = CustomException::class)
    fun should_ReturnError_WhenListHasMoreThanTwoFlavors() {
        runBlocking {
            val flavors = mutableListOf(
                PizzaFlavor("Mozzarella", 10.0),
                PizzaFlavor("Pepperoni", 12.0),
                PizzaFlavor("Vegetarian", 9.5)
            )
            val result = getPizzaOrderUseCase(flavors)
            result.collect { }
        }
    }

    @Test
    fun should_ReturnOrder_WhenListHasOnlyOneFlavor() {
        runBlocking {
            val flavors = mutableListOf(
                PizzaFlavor("Mozzarella", 10.0)
            )
            val result = getPizzaOrderUseCase(flavors)
            result.collect { order ->
                assertEquals(10.0, order.price)
                assertEquals("Mozzarella", order.flavors!![0].name)
            }
        }
    }

    @Test
    fun should_ReturnOrderWithSumOfEachHalfFlavor_WhenListHasTwoFlavors() {
        runBlocking {
            val flavors = mutableListOf(
                PizzaFlavor("Mozzarella", 10.0),
                PizzaFlavor("Pepperoni", 12.0)
            )
            val result = getPizzaOrderUseCase(flavors)
            result.collect { order ->
                assertEquals(11.0, order.price)
                assertEquals("Mozzarella", order.flavors!![0].name)
                assertEquals("Pepperoni", order.flavors!![1].name)
            }
        }
    }
}