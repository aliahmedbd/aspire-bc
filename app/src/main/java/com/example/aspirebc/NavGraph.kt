package com.example.aspirebc

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.aspirebc.ui.admin.AdminScreen
import com.example.aspirebc.ui.home.HomeScreen
import com.example.aspirebc.ui.login.LoginScreen
import com.example.aspirebc.ui.member.MemberDetailScreen
import com.example.aspirebc.ui.member.MembersScreen
import com.example.aspirebc.ui.payment.PaymentsScreen
import com.example.aspirebc.ui.profile.ProfileScreen
import com.example.aspirebc.ui.session.CreateSessionScreen
import com.example.aspirebc.ui.session.SessionDetailsScreen
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object Login : Screen
    @Serializable
    data object Home : Screen
    @Serializable
    data object Members : Screen
    @Serializable
    data class MemberDetail(val id: String) : Screen
    @Serializable
    data object Payments : Screen
    @Serializable
    data object Profile : Screen
    @Serializable
    data object Admin : Screen
    @Serializable
    data object CreateSession : Screen
    @Serializable
    data object SessionDetails : Screen
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login,
        modifier = modifier
    ) {
        composable<Screen.Login> {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home) {
                        popUpTo<Screen.Login> { inclusive = true }
                    }
                }
            )
        }
        composable<Screen.Home> {
            HomeScreen(
                onNavigateToCreateSession = {
                    navController.navigate(Screen.CreateSession)
                }
            )
        }
        composable<Screen.Members> {
            MembersScreen(
                onBack = { navController.popBackStack() },
                onNavigateToDetail = { id ->
                    navController.navigate(Screen.MemberDetail(id))
                }
            )
        }
        composable<Screen.MemberDetail> {
            MemberDetailScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable<Screen.Payments> {
            PaymentsScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable<Screen.Profile> {
            ProfileScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable<Screen.Admin> {
            AdminScreen(
                onNavigateToCreateSession = {
                    navController.navigate(Screen.CreateSession)
                }
            )
        }
        composable<Screen.CreateSession> {
            CreateSessionScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable<Screen.SessionDetails> {
            SessionDetailsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
