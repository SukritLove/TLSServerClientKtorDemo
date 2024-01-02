package org.example.tlsdemo

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import util.PlatformNetworkingUtils

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(PlatformNetworkingUtils(LocalContext.current.applicationContext))
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(PlatformNetworkingUtils(LocalContext.current.applicationContext))
}