package util


expect class PlatformNetworkingUtils {
    suspend fun getCurrentWifiIpAddress(): String?
}
