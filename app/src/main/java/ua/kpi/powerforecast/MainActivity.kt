package ua.kpi.powerforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.powerforecast.composables.*
import ua.kpi.powerforecast.ui.theme.PowerForecastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PowerForecastTheme {
                var displayFor by remember { mutableStateOf(DisplayFor.BASIC_LEVEL) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                            .navigationBarsPadding()
                    ) {
                        DisplayModeSelector(
                            selected = displayFor,
                            onSelectedChange = { displayFor = it }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        when (displayFor) {
                            DisplayFor.BASIC_LEVEL -> BasicConsumptionApp()
                            DisplayFor.MIDDLE_LEVEL -> MediumConsumptionApp()
                            DisplayFor.ADVANCED_LEVEL -> AdvancedConsumptionApp()
                        }
                    }
                }
            }
        }
    }
}
