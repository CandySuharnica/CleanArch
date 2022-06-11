package dev.rodkin.syharnicacleanarch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.rodkin.syharnicacleanarch.composeUI.navigation.NavigationGraph
import dev.rodkin.syharnicacleanarch.composeUI.theme.SyharnicaCleanArchTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SyharnicaCleanArchTheme {
                val systemUiController = rememberSystemUiController()

                val primaryBackground = MaterialTheme.colorScheme.background
                // Set status bar color
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = primaryBackground,
                        darkIcons = true
                    )
                }

                NavigationGraph()
            }
        }
    }
}
