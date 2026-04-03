package com.example.aspirebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspirebc.ui.theme.*

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.SportsTennis,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "ASPIRE BADMINTON CLUB",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Black,
                                fontSize = 18.sp,
                                letterSpacing = (-0.5).sp
                            ),
                            color = Primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle Notifications */ }) {
                        Icon(imageVector = Icons.Default.Notifications, contentDescription = null, tint = Secondary)
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerHigh)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Surface.copy(alpha = 0.8f))
            )
        },
        bottomBar = {
            BottomNavigationBar(currentRoute = "home")
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add Session */ },
                containerColor = Primary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Column {
                    Text(
                        text = "Up Next",
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Black),
                        color = OnSurface
                    )
                    Text(
                        text = "Join the next available court",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                        color = OnSurfaceVariant
                    )
                }
            }

            item {
                SessionCard(
                    title = "Tuesday Evening Smash",
                    type = "WEEKDAY SESSION",
                    date = "Oct 24, 18:00 - 20:00",
                    location = "Court 4",
                    spotsLeft = 4,
                    isWeekday = true
                )
            }

            item {
                SessionCard(
                    title = "Weekend Open Play",
                    type = "WEEKEND SESSION",
                    date = "Oct 28, 09:00 - 12:00",
                    location = "Main Hall - Court 1 & 2",
                    spotsLeft = 2,
                    isWeekday = false
                )
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun SessionCard(
    title: String,
    type: String,
    date: String,
    location: String,
    spotsLeft: Int,
    isWeekday: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(if (isWeekday) SecondaryContainer else Primary)
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = type,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 10.sp,
                            letterSpacing = 1.sp
                        ),
                        color = if (isWeekday) OnSecondaryContainer else Color.White
                    )
                }

                Row(modifier = Modifier.padding(start = 8.dp)) {
                    // Avatars placeholder
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerHigh)
                                .border(2.dp, SurfaceContainerLowest, CircleShape)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerHigh)
                            .border(2.dp, SurfaceContainerLowest, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "+8", style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = OnSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Event, contentDescription = null, tint = Primary, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = date, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = OnSurfaceVariant)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = Primary, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = location, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = OnSurfaceVariant)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "AVAILABILITY",
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, letterSpacing = 1.sp),
                        color = Primary.copy(alpha = 0.6f)
                    )
                    Text(
                        text = "$spotsLeft SPOTS LEFT",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Black),
                        color = OnSurface
                    )
                }

                Button(
                    onClick = { /* Join Session */ },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TertiaryFixed)
                ) {
                    Text(
                        text = "JOIN SESSION",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = OnTertiaryFixed
                    )
                }
            }
        }
    }
}

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
