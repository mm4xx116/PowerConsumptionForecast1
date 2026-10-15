package ua.kpi.powerforecast.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.kpi.powerforecast.data.ConsumptionCalculator

@Composable
fun MediumConsumptionApp() {
    var baseLoad by remember { mutableStateOf("") }
    var consumersCount by remember { mutableStateOf("") }
    var selectedScenario by remember { mutableStateOf("Звичайний") }
    var result by remember { mutableStateOf(0.0) }
    val calculator = remember { ConsumptionCalculator() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Моніторинг споживання електроенергії",
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Нічний", "Звичайний", "Піковий").forEach { scenario ->
                Button(
                    onClick = {
                        selectedScenario = scenario
                        val base = baseLoad.toDoubleOrNull() ?: 100.0
                        val factor = when(scenario) {
                            "Нічний" -> 0.6
                            "Піковий" -> 1.4
                            else -> 1.0
                        }
                        result = base * factor
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(scenario, style = MaterialTheme.typography.labelSmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = baseLoad,
            onValueChange = { baseLoad = it },
            label = { Text("Середнє навантаження об'єкта (кВт)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = consumersCount,
            onValueChange = { consumersCount = it },
            label = { Text("Кількість підключених об'єктів") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                result = calculator.calculateMedium(baseLoad, consumersCount)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати споживання")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Поточний сценарій: $selectedScenario", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = "Прогнозована потужність: ${"%.2f".format(result)} кВт",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
