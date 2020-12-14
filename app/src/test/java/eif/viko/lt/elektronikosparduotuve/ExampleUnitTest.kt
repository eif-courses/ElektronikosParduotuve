package eif.viko.lt.elektronikosparduotuve

import eif.viko.lt.elektronikosparduotuve.logika.Testas
import org.junit.Test

import org.junit.Assert.*
import kotlin.ArithmeticException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun AddTwoNumbers2And2ThenReturn4() {
        assertEquals(2, 2 + 2)
    }
    @Test
    fun AddTwoNumbers2And3ThenReturn5() {
        assertEquals(5, 2 + 3)
    }

    @Test(expected = ArithmeticException::class)
    fun DivideTwoNumbers() {
        assertEquals(0, Testas.dalinti())
    }

    @Test
    fun MultiplyTwoNumbers1() {
        assertEquals(5, Testas.dauginti(5,1))
    }
    @Test
    fun MultiplyTwoNumbers2() {
        assertEquals(6, Testas.dauginti(2, 3))
    }



    //Scenario: direct search article
    @Test
    fun GivenEnterSearchTermCucumberWhenDoSearchThenSingleResultisshownforCucumber(){

    }






}