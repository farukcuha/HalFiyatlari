package com.pandorina.kotlindslcoreproject


import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import kotlin.properties.Delegates

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    var SUT by Delegates.notNull<Int>()

    @Before
    fun addition_isCorrect() {
        SUT = 45

    }

    @Test
    fun testtime(){
        assertEquals(45, SUT)
    }


}