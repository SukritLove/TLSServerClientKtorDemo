import androidx.compose.ui.window.ComposeUIViewController
import util.PlatformNetworkingUtils

fun MainViewController() = ComposeUIViewController { PlatformNetworkingUtils() }
