import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import util.PlatformNetworkingUtils

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "TSLServerClientKtorDemo") {
        App(networkUtils = PlatformNetworkingUtils())
    }
}

@Preview
@Composable
fun AppDesktopPreview() {
    App(networkUtils = PlatformNetworkingUtils())
}