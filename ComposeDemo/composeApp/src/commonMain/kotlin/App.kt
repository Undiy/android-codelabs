import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.datetime.Clock
import kotlinx.datetime.IllegalTimeZoneException
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun App() {
    MaterialTheme {
        var location by remember { mutableStateOf("Europe/Paris") }
        var timeAtLocation by remember { mutableStateOf("No location selected") }

        Column {
            Text(timeAtLocation)
            TextField(value = location, onValueChange = {
                location = it
            })
            Button(onClick = {
                timeAtLocation = currentTimeAt(location) ?: "Invalid Location"
            }) {
                Text("Show Time At Location")
            }
        }
    }
}

private fun currentTimeAt(location: String): String? {
    fun LocalTime.formatted() = "$hour:$minute:$second"

    return try {
        val time = Clock.System.now()
        val zone = TimeZone.of(location)
        val localTime = time.toLocalDateTime(zone).time
        "The time in $location is ${localTime.formatted()}"
    } catch (ex: IllegalTimeZoneException) {
        null
    }
}
