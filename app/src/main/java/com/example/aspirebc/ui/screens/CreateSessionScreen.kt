package com.example.aspirebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aspirebc.ui.theme.*

@Composable
fun CreateSessionScreen() {
    var sessionCategory by remember { mutableStateOf("weekday") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create Session",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp,
                            letterSpacing = (-0.5).sp
                        ),
                        color = Primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Back */ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = Primary)
                    }
                }
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(SurfaceContainerLowest.copy(alpha = 0.9f))
                    .padding(8.dp)
            ) {
                Button(
                    onClick = { /* Save */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TertiaryFixed)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Save & Publish Session",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                            color = OnTertiaryFixed
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.Default.Send, contentDescription = null, tint = OnTertiaryFixed)
                    }
                }
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
                        text = "SESSION LOGISTICS",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = OnSurfaceVariant,
                            letterSpacing = 2.sp
                        )
                    )
                    Text(
                        text = "NEW COURT EVENT",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontSize = 48.sp,
                            lineHeight = 44.sp,
                            color = Primary
                        )
                    )
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "SESSION CATEGORY",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = OnSurfaceVariant,
                            letterSpacing = 1.sp
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SurfaceContainerLow)
                            .padding(4.dp)
                    ) {
                        Button(
                            onClick = { sessionCategory = "weekday" },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (sessionCategory == "weekday") Color.White else Color.Transparent,
                                contentColor = if (sessionCategory == "weekday") Primary else Secondary
                            ),
                            elevation = if (sessionCategory == "weekday") ButtonDefaults.buttonElevation(defaultElevation = 2.dp) else null
                        ) {
                            Text(text = "Weekday Session", style = MaterialTheme.typography.labelMedium)
                        }
                        Button(
                            onClick = { sessionCategory = "weekend" },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (sessionCategory == "weekend") Color.White else Color.Transparent,
                                contentColor = if (sessionCategory == "weekend") Primary else Secondary
                            ),
                            elevation = if (sessionCategory == "weekend") ButtonDefaults.buttonElevation(defaultElevation = 2.dp) else null
                        ) {
                            Text(text = "Weekend Session", style = MaterialTheme.typography.labelMedium)
                        }
                    }
                }
            }

            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(text = "DATE", style = MaterialTheme.typography.labelMedium.copy(color = OnSurfaceVariant, letterSpacing = 1.sp))
                        OutlinedTextField(
                            value = "05/24/2024",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            trailingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = SurfaceContainerHighest,
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )
                    }
                    Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(text = "START TIME", style = MaterialTheme.typography.labelMedium.copy(color = OnSurfaceVariant, letterSpacing = 1.sp))
                        OutlinedTextField(
                            value = "06:00 PM",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            trailingIcon = { Icon(Icons.Default.Schedule, contentDescription = null) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = SurfaceContainerHighest,
                                unfocusedContainerColor = SurfaceContainerHighest,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent
                            )
                        )
                    }
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(text = "DURATION (HOURS)", style = MaterialTheme.typography.labelMedium.copy(color = OnSurfaceVariant, letterSpacing = 1.sp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("1.0", "2.0", "3.0", "4.0+").forEach { duration ->
                            val isSelected = duration == "2.0"
                            Button(
                                onClick = { /* Select */ },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) TertiaryFixed else SurfaceContainerLow,
                                    contentColor = if (isSelected) OnTertiaryFixed else Primary
                                )
                            ) {
                                Text(text = duration, style = MaterialTheme.typography.labelMedium)
                            }
                        }
                    }
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(text = "VENUE LOCATION", style = MaterialTheme.typography.labelMedium.copy(color = OnSurfaceVariant, letterSpacing = 1.sp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("e.g. Central Arena") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = { Icon(Icons.Default.LocationOn, contentDescription = null, tint = Primary) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SurfaceContainerLow,
                            unfocusedContainerColor = SurfaceContainerLow,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                }
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(text = "ALLOCATED COURTS", style = MaterialTheme.typography.labelMedium.copy(color = OnSurfaceVariant, letterSpacing = 1.sp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Primary),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.SportsTennis, contentDescription = null, tint = Color.White)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            OutlinedTextField(
                                value = "",
                                onValueChange = {},
                                placeholder = { Text("e.g. 4, 5") },
                                modifier = Modifier.weight(1f),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent
                                )
                            )
                            Text(
                                text = "MULTIPLE\nVALUES OK",
                                style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, lineHeight = 10.sp),
                                color = OnSurfaceVariant,
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(120.dp))
            }
        }
    }
}
