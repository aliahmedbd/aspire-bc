package com.example.aspirebc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.aspirebc.ui.screens.*
import com.example.aspirebc.ui.theme.AspireBCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AspireBCTheme {
                var currentScreen by remember { mutableStateOf("login") }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentScreen) {
                        "login" -> LoginScreen()
                        "home" -> HomeScreen()
                        "session_details" -> SessionDetailsScreen()
                        "create_session" -> CreateSessionScreen()
                        "members" -> MembersScreen()
                        "profile" -> ProfileScreen()
                        "payments" -> PaymentsScreen()
                        "admin" -> AdminScreen()
                    }
                }
            }
        }
    }
}
