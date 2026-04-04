package com.example.aspirebc.ui.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.example.aspirebc.domain.model.Member
import com.example.aspirebc.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    onNavigateToCreateSession: () -> Unit,
    viewModel: AdminViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                AdminContract.Effect.NavigateToCreateSession -> onNavigateToCreateSession()
                is AdminContract.Effect.ShowMessage -> {
                    // Show message
                }
            }
        }
    }

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
                            text = stringResource(R.string.app_name).uppercase(),
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
                    Column {
                        Text(
                            text = stringResource(R.string.admin_control),
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Black),
                            color = Primary
                        )
                        Text(
                            text = stringResource(R.string.admin_subtitle),
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                            color = OnSurfaceVariant
                        )
                    }
                }

                item {
                    Button(
                        onClick = { viewModel.onIntent(AdminContract.Intent.AddSessionClicked) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = TertiaryFixed)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.AddCircle, contentDescription = null, tint = OnTertiaryFixed)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = stringResource(R.string.add_session_btn),
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                                color = OnTertiaryFixed
                            )
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
                            Text(
                                text = stringResource(R.string.active_members_label),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = Color.White.copy(alpha = 0.5f),
                                    letterSpacing = 2.sp
                                )
                            )
                            Text(
                                text = state.activeMembersCount.toString(),
                                style = MaterialTheme.typography.displayLarge.copy(fontSize = 48.sp, color = Color.White)
                            )
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
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(R.string.pending_approvals),
                                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                                    color = Primary
                                )
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(SecondaryContainer)
                                        .padding(horizontal = 12.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = stringResource(R.string.new_regs_format, state.pendingApprovals.size),
                                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp),
                                        color = OnSecondaryContainer
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(24.dp))
                            state.pendingApprovals.forEach { member ->
                                ApprovalItem(
                                    member = member,
                                    onApprove = { viewModel.onIntent(AdminContract.Intent.ApproveMember(member.id)) },
                                    onReject = { viewModel.onIntent(AdminContract.Intent.RejectMember(member.id)) }
                                )
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
}

@Composable
fun ApprovalItem(
    member: Member,
    onApprove: () -> Unit,
    onReject: () -> Unit
) {
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
                Text(
                    text = member.name.take(2).uppercase(),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = Primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = member.name, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = OnSurface)
                Text(
                    text = "Level: ${member.level} • Registered 2h ago",
                    style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp),
                    color = OnSurfaceVariant
                )
            }
            IconButton(onClick = onReject) {
                Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = Error)
            }
            IconButton(
                onClick = onApprove,
                modifier = Modifier.background(TertiaryFixed, RoundedCornerShape(8.dp))
            ) {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = OnTertiaryFixed)
            }
        }
    }
}
