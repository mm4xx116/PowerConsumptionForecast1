package ua.kpi.powerforecast.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.kpi.powerforecast.DisplayFor

@Composable
fun DisplayModeSelector(
    selected: DisplayFor,
    onSelectedChange: (DisplayFor) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Вибір режиму відображення",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DisplayFor.values().forEach { mode ->
                Button(
                    onClick = { onSelectedChange(mode) },
                    modifier = Modifier.weight(1f),
                    enabled = mode != selected,
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = when (mode) {
                            DisplayFor.BASIC_LEVEL -> "Базовий"
                            DisplayFor.MIDDLE_LEVEL -> "Середній"
                            DisplayFor.ADVANCED_LEVEL -> "Просунутий"
                        },
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}
