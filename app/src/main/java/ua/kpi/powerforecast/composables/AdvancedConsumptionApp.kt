package ua.kpi.powerforecast.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ua.kpi.powerforecast.data.ConsumptionCalculator
import ua.kpi.powerforecast.forms.LoginForm
import ua.kpi.powerforecast.forms.RegistrationForm

@Composable
fun AdvancedConsumptionApp() {
    var isAuthScreen by remember { mutableStateOf(false) }
    var isRegisterMode by remember { mutableStateOf(false) }

    var baseLoad by remember { mutableStateOf("150") }
    var consumersCount by remember { mutableStateOf("45") }
    var tempDeviation by remember { mutableStateOf("5") }
    var targetPower by remember { mutableStateOf(120.0f) }

    val animatedPower by animateFloatAsState(
        targetValue = targetPower,
        label = "PowerForecastAnimation"
    )

    val calculator = remember { ConsumptionCalculator() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isAuthScreen) "Кабінет оператора" else "Аналітика енергомережі",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = { isAuthScreen = !isAuthScreen }) {
                Text(if (isAuthScreen) "До аналітики" else "Вхід/Кабінет")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (isAuthScreen) {
            if (isRegisterMode) {
                RegistrationForm(
                    onRegister = { name, email, pass, confirm ->
                        when {
                            name.isBlank() || email.isBlank() || pass.isBlank() || confirm.isBlank() -> "Заповніть усі поля!"
                            pass != confirm -> "Паролі не співпадають!"
                            else -> "Реєстрацію диспетчера успішно виконано!"
                        }
                    },
                    onSwitchToLogin = { isRegisterMode = false }
                )
            } else {
                LoginForm(
                    onLogin = { login, pass ->
                        if (login.isBlank() || pass.isBlank()) "Заповніть усі поля!"
                        else "Авторизація успішна. Доступ надано!"
                    },
                    onSwitchToRegister = { isRegisterMode = true }
                )
            }
        } else {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Прогноз споживання електроенергії (Live)", fontSize = 16.sp)
                    Text(
                        text = "${"%.1f".format(animatedPower)} кВт·год",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text("Статус мережі: Номінальне навантаження стабільне", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = baseLoad,
                onValueChange = { baseLoad = it },
                label = { Text("Базове споживання (кВт)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = consumersCount,
                onValueChange = { consumersCount = it },
                label = { Text("Активні вузли навантаження") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = tempDeviation,
                onValueChange = { tempDeviation = it },
                label = { Text("Температурна поправка (%)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val calc = calculator.calculateAdvanced(baseLoad, consumersCount, tempDeviation)
                    targetPower = calc.toFloat()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Оновити та анімувати прогноз")
            }
        }
    }
}
