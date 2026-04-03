package com.example.aspirebc.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.SportsTennis
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspirebc.ui.theme.*

@Composable
fun LoginScreen() {
    var selectedMembership by remember { mutableStateOf("weekday") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Kinetic Background Elements
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(PrimaryContainer.copy(alpha = 0.1f), Color.Transparent),
                        center = androidx.compose.ui.geometry.Offset(1000f, 0f),
                        radius = 1000f
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Brand Header
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.SportsTennis,
                    contentDescription = null,
                    tint = TertiaryFixed,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "ASPIRE BC",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 48.sp,
                    lineHeight = 44.sp,
                    color = Primary
                ),
                textAlign = TextAlign.Center
            )

            Text(
                text = "HIGH-PERFORMANCE BADMINTON CLUB",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Secondary,
                    letterSpacing = 2.sp
                ),
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Login Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(
                        text = "Welcome to Aspire",
                        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp),
                        color = OnSurface
                    )
                    Text(
                        text = "Join the court and master your game with our elite training ecosystem.",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                        color = OnSurfaceVariant,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = "SELECT MEMBERSHIP TYPE",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Primary,
                            letterSpacing = 2.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    MembershipOption(
                        title = "Weekday Member",
                        subtitle = "Mon - Fri Access • 6AM - 10PM",
                        icon = Icons.Default.CalendarMonth,
                        isSelected = selectedMembership == "weekday",
                        onClick = { selectedMembership = "weekday" }
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    MembershipOption(
                        title = "Weekend Member",
                        subtitle = "Sat - Sun Access • All Day Priority",
                        icon = Icons.Default.EventAvailable,
                        isSelected = selectedMembership == "weekend",
                        onClick = { selectedMembership = "weekend" }
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Button(
                        onClick = { /* Handle Google Login */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = TertiaryFixed)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Google Icon placeholder
                            Text(
                                text = "Continue with Google",
                                style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
                                color = OnTertiaryFixed
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "By continuing, you agree to our Terms of Play and Privacy Policy.",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp),
                        color = OnSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun MembershipOption(
    title: String,
    subtitle: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) SurfaceContainerLowest else SurfaceContainerLow)
            .border(
                width = 2.dp,
                color = if (isSelected) TertiaryFixed else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) Primary.copy(alpha = 0.1f) else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Primary,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = OnSurface
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp),
                    color = OnSurfaceVariant
                )
            }

            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = if (isSelected) Primary else OutlineVariant,
                        shape = CircleShape
                    )
                    .background(if (isSelected) Primary else Color.Transparent),
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }
    }
}
