package com.example.tugaspam3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tugaspam3.screen.*
import com.example.tugaspam3.data.CurrentUser
import com.example.tugaspam3.data.UserData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "daftar") {
        composable(route = "daftar") {
            DaftarScreen(
                navController = navController,
                onRegister = { user ->
                    // Simpan data user
                    CurrentUser.data = user
                    // Navigate ke login setelah register
                    navController.navigate(route = "login")
                }
            )
        }

        composable(route = "login") {
            LoginScreen(
                navController = navController,
                onLogin = {
                    // Navigate ke detail setelah login sukses
                    navController.navigate(route = "detail")
                },
                onBackToRegister = {
                    // Kembali ke halaman daftar
                    navController.popBackStack()
                }
            )
        }

        composable(route = "detail") {
            DetailScreen(
                navController = navController,
                user = CurrentUser.data,
                onLogout = {
                    // Kembali ke halaman daftar saat logout
                    navController.popBackStack(route = "daftar", inclusive = false)
                }
            )
        }
    }
}