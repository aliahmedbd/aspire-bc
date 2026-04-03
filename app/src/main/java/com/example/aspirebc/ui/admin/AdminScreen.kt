package com.example.aspirebc.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspirebc.ui.components.BottomNavigationBar
import com.example.aspirebc.ui.theme.*

@Composable
fun AdminScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(SurfaceContainerHigh)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "ASPIRE BC",
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
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(currentRoute = "admin")
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
                        text = "Admin Control",
                        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Black),
                        color = Primary
                    )
                    Text(
                        text = "Manage sessions, memberships, and club logistics.",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                        color = OnSurfaceVariant
                    )
                }
            }

            item {
                Button(
                    onClick = { /* Add Session */ },
                    modifier = Modifier.fillMaxWidth().height(64.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TertiaryFixed)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.AddCircle, contentDescription = null, tint = OnTertiaryFixed)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "ADD A SESSION", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black), color = OnTertiaryFixed)
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Primary)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(text = "ACTIVE MEMBERS", style = MaterialTheme.typography.labelMedium.copy(color = Color.White.copy(alpha = 0.5f), letterSpacing = 2.sp))
                        Text(text = "142", style = MaterialTheme.typography.displayLarge.copy(fontSize = 48.sp, color = Color.White))
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Pending Approvals", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(SecondaryContainer)
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                Text(text = "3 NEW REGS", style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp), color = OnSecondaryContainer)
                            }
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        repeat(2) {
                            ApprovalItem()
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun ApprovalItem() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(SurfaceContainerHigh),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "JD", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), color = Primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Julian D.", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = OnSurface)
                Text(text = "Level: Intermediate • Registered 2h ago", style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp), color = OnSurfaceVariant)
            }
            IconButton(onClick = { /* Reject */ }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = Error)
            }
            IconButton(
                onClick = { /* Approve */ },
                modifier = Modifier.background(TertiaryFixed, RoundedCornerShape(8.dp))
            ) {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = OnTertiaryFixed)
            }
        }
    }
}
