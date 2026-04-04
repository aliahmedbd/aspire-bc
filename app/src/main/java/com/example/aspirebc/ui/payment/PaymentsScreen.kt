package com.example.aspirebc.ui.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
fun PaymentsScreen(
    onBack: () -> Unit,
    viewModel: PaymentsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PaymentsContract.Effect.ShowMessage -> {
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
                        text = stringResource(R.string.payments_title),
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
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    state.performance?.let { perf ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(24.dp),
                            colors = CardDefaults.cardColors(containerColor = Primary)
                        ) {
                            Column(modifier = Modifier.padding(24.dp)) {
                                Text(
                                    text = stringResource(R.string.member_performance),
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = Color.White.copy(alpha = 0.5f),
                                        letterSpacing = 2.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = stringResource(R.string.months_progress),
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        fontSize = 48.sp,
                                        lineHeight = 44.sp,
                                        color = Color.White
                                    )
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                LinearProgressIndicator(
                                    progress = { perf.progress },
                                    modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
                                    color = TertiaryFixed,
                                    trackColor = Color.White.copy(alpha = 0.1f)
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                    Card(
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f))
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text(text = stringResource(R.string.current_streak), style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, color = Color.White.copy(alpha = 0.6f)))
                                            Text(text = perf.streak, style = MaterialTheme.typography.titleLarge.copy(color = Color.White))
                                        }
                                    }
                                    Card(
                                        modifier = Modifier.weight(1f),
                                        shape = RoundedCornerShape(12.dp),
                                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f))
                                    ) {
                                        Column(modifier = Modifier.padding(16.dp)) {
                                            Text(text = stringResource(R.string.total_saved_label), style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, color = Color.White.copy(alpha = 0.6f)))
                                            Text(text = "$${perf.totalSaved}", style = MaterialTheme.typography.titleLarge.copy(color = Color.White))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                items(state.payments) { payment ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest)
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Column {
                                    Text(text = payment.date, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
                                    Text(text = payment.description, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = OnSurfaceVariant)
                                }
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(if (payment.status == "UNPAID") Error.copy(alpha = 0.1f) else TertiaryFixed.copy(alpha = 0.1f))
                                        .padding(horizontal = 12.dp, vertical = 4.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(modifier = Modifier.size(6.dp).clip(CircleShape).background(if (payment.status == "UNPAID") Error else TertiaryFixed))
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(text = payment.status, style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp), color = if (payment.status == "UNPAID") Error else OnSurface)
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "$${payment.amount}", style = MaterialTheme.typography.displayLarge.copy(fontSize = 40.sp), color = Primary)
                                if (payment.status == "UNPAID") {
                                    Button(onClick = { viewModel.onIntent(PaymentsContract.Intent.ConfirmPayment(payment.id)) }) {
                                        Text(stringResource(R.string.confirm_payment))
                                    }
                                } else {
                                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = TertiaryFixed)
                                }
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(imageVector = Icons.Default.AutoAwesome, contentDescription = null, tint = Tertiary)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = stringResource(R.string.early_bird_perk), style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = Tertiary)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = stringResource(R.string.streak_reward_message), style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = OnSurfaceVariant)
                            Spacer(modifier = Modifier.height(16.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(TertiaryFixed)
                                    .padding(16.dp)
                            ) {
                                Column {
                                    Text(text = stringResource(R.string.next_month), style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, fontWeight = FontWeight.Black), color = OnTertiaryFixed)
                                    Text(text = stringResource(R.string.free), style = MaterialTheme.typography.displayLarge.copy(fontSize = 32.sp), color = OnTertiaryFixed)
                                }
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
}
