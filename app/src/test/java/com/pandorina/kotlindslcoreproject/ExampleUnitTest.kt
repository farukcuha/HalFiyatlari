package com.pandorina.kotlindslcoreproject

import android.util.Log
import com.pandorina.hal_fiyatlari.data.remote.price.PricesServiceImpl
import com.pandorina.hal_fiyatlari.data.repostiory.PricesRepositoryImpl
import com.pandorina.hal_fiyatlari.domain.use_case.prices.GetPrices
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}