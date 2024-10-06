package com.sunaa.apifetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sunaa.apifetch.fetchdata.presentation.ScreenSimpleNavigation
import com.sunaa.apifetch.ui.theme.APIFetchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APIFetchTheme {
                ScreenSimpleNavigation()
            }
        }
    }
}

