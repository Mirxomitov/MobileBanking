package uz.gita.mobilebanking

import org.junit.Assert.assertEquals
import org.junit.Test
import uz.gita.mobilebanking.utils.toValue

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }

    @Test
    fun toValue_isCorrect() {
        assertEquals("1 000", "1000".toValue())
        assertEquals("20 000", "20000".toValue())
        assertEquals("500 000", "500000".toValue())
        assertEquals("1 234 567", "1234567".toValue())
        assertEquals("9 876 543 210", "9876543210".toValue())
    }
}