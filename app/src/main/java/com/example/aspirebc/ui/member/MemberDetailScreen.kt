package com.example.aspirebc.ui.member

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aspirebc.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberDetailScreen(
    onBack: () -> Unit,
    viewModel: MemberDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                MemberDetailContract.Effect.NavigateBack -> onBack()
                is MemberDetailContract.Effect.ShowMessage -> {
                    // Show message logic
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "MEMBER PROFILE",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp,
                            letterSpacing = (-0.5).sp
                        ),
                        color = Primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Primary)
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.onIntent(MemberDetailContract.Intent.ShareClicked) }) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = null, tint = Primary)
                    }
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            state.member?.let { member ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape)
                                    .background(SurfaceContainerHigh)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = member.name,
                                style = MaterialTheme.typography.displayLarge.copy(
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Primary
                                )
                            )
                            Text(
                                text = "${member.level} MEMBER • SINCE 2023",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = OnSurfaceVariant,
                                    letterSpacing = 2.sp
                                )
                            )
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            StatCard(modifier = Modifier.weight(1f), label = "WIN RATE", value = "78%", icon = Icons.Default.TrendingUp)
                            StatCard(modifier = Modifier.weight(1f), label = "SESSIONS", value = "142", icon = Icons.Default.SportsTennis)
                        }
                    }

                    item {
                        Text(
                            text = "Activity Stats",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            color = Primary
                        )
                    }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
                        ) {
                            Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                ActivityRow(label = "Power Smash", value = "85/100", color = Tertiary)
                                ActivityRow(label = "Agility", value = "92/100", color = Primary)
                                ActivityRow(label = "Endurance", value = "74/100", color = Secondary)
                            }
                        }
                    }

                    item {
                        Button(
                            onClick = { viewModel.onIntent(MemberDetailContract.Intent.MessageClicked) },
                            modifier = Modifier.fillMaxWidth().height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Primary)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.Message, contentDescription = null, tint = Color.White)
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(text = "Send Message", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Color.White)
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(40.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(modifier: Modifier = Modifier, label: String, value: String, icon: ImageVector) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Icon(imageVector = icon, contentDescription = null, tint = Primary, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = label, style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, color = OnSurfaceVariant, letterSpacing = 1.sp))
            Text(text = value, style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp, fontWeight = FontWeight.Black), color = Primary)
        }
    }
}

@Composable
fun ActivityRow(label: String, value: String, color: Color) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = label, style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold), color = OnSurface)
            Text(text = value, style = MaterialTheme.typography.labelMedium, color = OnSurfaceVariant)
        }
        LinearProgressIndicator(
            progress = { 0.8f },
            modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
            color = color,
            trackColor = Color.White.copy(alpha = 0.5f)
        )
    }
}
