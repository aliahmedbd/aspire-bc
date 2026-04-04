package com.example.aspirebc.ui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aspirebc.R
import com.example.aspirebc.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionDetailsScreen(
    onBack: () -> Unit,
    viewModel: SessionDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                SessionDetailsContract.Effect.NavigateBack -> onBack()
                is SessionDetailsContract.Effect.ShowMessage -> {
                    // Show message
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.session_details_title),
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
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SurfaceContainerHigh)
                    )
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
                    onClick = { viewModel.onIntent(SessionDetailsContract.Intent.ConfirmSpotClicked) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TertiaryFixed),
                    enabled = !state.isLoading && (state.session?.spotsLeft ?: 0) > 0
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(24.dp), color = OnTertiaryFixed)
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(R.string.confirm_spot),
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                                color = OnTertiaryFixed
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(imageVector = Icons.Default.Bolt, contentDescription = null, tint = OnTertiaryFixed)
                        }
                    }
                }
            }
        }
    ) { padding ->
        if (state.session == null && state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            state.session?.let { session ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 24.dp)
                ) {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(SecondaryContainer)
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.open_play),
                                style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, letterSpacing = 1.sp),
                                color = OnSecondaryContainer
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = session.title,
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 48.sp,
                                lineHeight = 44.sp,
                                color = Primary
                            )
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Avatars placeholder
                            repeat(3) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(SurfaceContainerHigh)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = stringResource(R.string.spots_filled_format, session.totalSpots - session.spotsLeft, session.totalSpots),
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                                color = Primary
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        // Schedule Card
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest)
                        ) {
                            Column(modifier = Modifier.padding(24.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null, tint = Primary, modifier = Modifier.size(32.dp))
                                    Text(text = stringResource(R.string.schedule_label), style = MaterialTheme.typography.labelMedium.copy(color = OnSurfaceVariant, letterSpacing = 1.sp))
                                }
                                Spacer(modifier = Modifier.height(24.dp))
                                Text(text = session.date, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
                                Text(text = "18:30 — 21:00 (2.5h)", style = MaterialTheme.typography.bodyLarge, color = OnSurfaceVariant)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Venue Card
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
                        ) {
                            Column(modifier = Modifier.padding(24.dp)) {
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = Primary, modifier = Modifier.size(32.dp))
                                    Text(text = stringResource(R.string.venue_label), style = MaterialTheme.typography.labelMedium.copy(color = OnSurfaceVariant, letterSpacing = 1.sp))
                                }
                                Spacer(modifier = Modifier.height(24.dp))
                                Text(text = session.location, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
                                Text(text = "Aspire Sports Center, North Wing", style = MaterialTheme.typography.bodyLarge, color = OnSurfaceVariant)
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = stringResource(R.string.attending_players), style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(SurfaceContainerHigh)
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                Text(text = "3 Verified", style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp), color = OnSurfaceVariant)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Player List
                        repeat(3) {
                            PlayerItem()
                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        Spacer(modifier = Modifier.height(120.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun PlayerItem() {
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
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceContainerHigh)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Ali Ahmed", style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
                Text(text = "Advanced • Win Streak: 4", style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp), color = OnSurfaceVariant)
            }
            Icon(imageVector = Icons.Default.Stars, contentDescription = null, tint = TertiaryFixedDim)
        }
    }
}
