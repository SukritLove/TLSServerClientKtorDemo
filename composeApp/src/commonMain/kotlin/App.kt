import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import util.PlatformNetworkingUtils
import util.startServer

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(networkUtils: PlatformNetworkingUtils) {
    var deviceIpAddress by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        deviceIpAddress = networkUtils.getCurrentWifiIpAddress()
    }

    LaunchedEffect(deviceIpAddress) {
        launch {
            withContext(Dispatchers.IO) {
                startServer(deviceIpAddress.orEmpty())
            }
        }
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        deviceIpAddress?.let { Text("Server is listening on $it :: 4001") }
    }

}