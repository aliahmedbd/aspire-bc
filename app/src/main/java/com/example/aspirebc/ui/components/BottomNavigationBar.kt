package com.example.aspirebc.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.aspirebc.R
import com.example.aspirebc.Screen
import com.example.aspirebc.ui.theme.*

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = Surface.copy(alpha = 0.9f),
        tonalElevation = 8.dp,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        val items = listOf(
            Triple(Screen.Home, stringResource(R.string.nav_home), Icons.Default.Home),
            Triple(Screen.Members, stringResource(R.string.nav_members), Icons.Default.Group),
            Triple(Screen.Payments, stringResource(R.string.nav_payments), Icons.Default.Payments),
            Triple(Screen.Profile, stringResource(R.string.nav_profile), Icons.Default.Person),
            Triple(Screen.Admin, stringResource(R.string.nav_admin), Icons.Default.AdminPanelSettings)
        )

        items.forEach { (screen, label, icon) ->
            val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(screen::class) } == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(screen) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = if (isSelected) Color.Black else Secondary
                    )
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp),
                        color = if (isSelected) Color.Black else Secondary
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = if (isSelected) TertiaryFixed else Color.Transparent
                )
            )
        }
    }
}
