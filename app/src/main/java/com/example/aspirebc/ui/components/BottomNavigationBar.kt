package com.example.aspirebc.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspirebc.ui.theme.*

@Composable
fun BottomNavigationBar(currentRoute: String) {
    NavigationBar(
        containerColor = Surface.copy(alpha = 0.9f),
        tonalElevation = 8.dp,
        modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        val items = listOf(
            Triple("home", "Home", Icons.Default.Home),
            Triple("members", "Members", Icons.Default.Group),
            Triple("payments", "Payments", Icons.Default.Payments),
            Triple("profile", "Profile", Icons.Default.Person),
            Triple("admin", "Admin", Icons.Default.AdminPanelSettings)
        )

        items.forEach { (route, label, icon) ->
            val isSelected = currentRoute == route
            NavigationBarItem(
                selected = isSelected,
                onClick = { /* Navigate */ },
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
