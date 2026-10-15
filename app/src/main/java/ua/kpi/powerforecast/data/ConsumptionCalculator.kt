package ua.kpi.powerforecast.data

class ConsumptionCalculator {

    fun calculateBasic(baseLoad: String): Double {
        val base = baseLoad.toDoubleOrNull() ?: 0.0
        return base * 1.15
    }

    fun calculateMedium(baseLoad: String, consumersCount: String): Double {
        val base = baseLoad.toDoubleOrNull() ?: 0.0
        val consumers = consumersCount.toDoubleOrNull() ?: 0.0
        return base * consumers * 0.85
    }

    fun calculateAdvanced(baseLoad: String, consumersCount: String, temperatureFactor: String): Double {
        val base = baseLoad.toDoubleOrNull() ?: 0.0
        val consumers = consumersCount.toDoubleOrNull() ?: 0.0
        val temp = temperatureFactor.toDoubleOrNull() ?: 1.0
        return (base * consumers * 0.85) * (1.0 + (temp / 100.0))
    }
}
