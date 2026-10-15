package ua.kpi.powerforecast

import org.junit.Test
import org.junit.Assert.*
import ua.kpi.powerforecast.data.ConsumptionCalculator

class ConsumptionCalculatorTest {

    private val calculator = ConsumptionCalculator()

    @Test
    fun testCalculateBasic() {
        val result = calculator.calculateBasic("100")
        assertEquals(115.0, result, 0.001)
    }

    @Test
    fun testCalculateMedium() {
        val result = calculator.calculateMedium("10", "5")
        assertEquals(42.5, result, 0.001)
    }

    @Test
    fun testCalculateAdvanced() {
        val result = calculator.calculateAdvanced("10", "5", "10")
        assertEquals(46.75, result, 0.001)
    }
}
