package util

actual class PlatformNetworkingUtils {
    actual suspend fun getCurrentWifiIpAddress(): String? {
        return "192.168.101.101"
    }
}