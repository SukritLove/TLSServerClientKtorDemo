import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import data.ClientMessage
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
    var deviceIpAddress by remember { mutableStateOf<String?>("") }
    var inputIpAddress by remember { mutableStateOf("") }
    var inputPort by remember { mutableStateOf("4001") }
    var inputMessage by remember { mutableStateOf("") }
    var clientStarted by remember { mutableStateOf(false) }
    val message = ClientMessage.messages.message


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
        Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        deviceIpAddress?.let { Text("Server is listening on $it :: 4001") }

        Spacer(modifier = Modifier.padding(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(MaterialTheme.colors.surface).border(5.dp, Color.Black)
        ) {
            Text(
                text = message,
                style = TextStyle(Color.Black),
                modifier = Modifier.padding(10.dp)
            )
        }


        AddSpace()
        CreateTextField(
            value = inputIpAddress,
            onTextChange = { inputIpAddress = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = "Client IP"
        )

        Spacer(modifier = Modifier.padding(10.dp))
        CreateTextField(
            value = inputPort,
            onTextChange = { inputPort = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = "Client Port"
        )

        AddSpace()
        CreateTextField(
            value = inputMessage,
            onTextChange = { inputMessage = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeholder = "Message"
        )

        Spacer(modifier = Modifier.padding(10.dp))
        CreateButton(
            btnText = "Send Message",
            onClickAction = {
                clientStarted = !clientStarted
            })


    }

}

@Composable
fun CreateTextField(
    value: String,
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    placeholder: String
) {
    TextField(value = value,
        onValueChange = onTextChange,
        keyboardOptions = keyboardOptions,
        placeholder = { Text(text = placeholder) })
}

@Composable
fun CreateButton(btnText: String, onClickAction: () -> Unit) {
    Button(onClick = onClickAction) {
        Text(text = btnText)
    }
}


@Composable
fun AddSpace() {
    Spacer(modifier = Modifier.padding(20.dp))
}