package com.safagurdag.theevapp.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.safagurdag.theevapp.ui.navigation.AppNavigationHost
import com.safagurdag.theevapp.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * App wrap activity.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TheEVApp()
        }
    }
}

/**
 * App root content wrapper.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TheEVApp() {

    val appState = rememberAppState()

    AppTheme {
        Scaffold(
            scaffoldState = appState.scaffoldState
        ) {
            AppNavigationHost(
                appState = appState
            )
        }
    }
}