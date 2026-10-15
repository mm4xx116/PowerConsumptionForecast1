package ua.kpi.powerforecast.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.kpi.powerforecast.data.ConsumptionCalculator

@Composable
fun BasicConsumptionApp() {
    var baseLoad by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }
    val calculator = remember { ConsumptionCalculator() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Система прогнозування споживання",
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = baseLoad,
            onValueChange = { baseLoad = it },
            label = { Text("Базове навантаження (кВт·год)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { result = calculator.calculateBasic(baseLoad) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Розрахувати прогноз")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Прогнозоване споживання: ${"%.2f".format(result)} кВт·год",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
