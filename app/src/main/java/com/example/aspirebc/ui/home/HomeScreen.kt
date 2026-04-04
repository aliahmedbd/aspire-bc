package com.example.aspirebc.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.example.aspirebc.domain.model.Session
import com.example.aspirebc.ui.theme.*
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToCreateSession: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                HomeContract.Effect.NavigateToCreateSession -> onNavigateToCreateSession()
                is HomeContract.Effect.ShowError -> {
                    // Show error message
                }
                HomeContract.Effect.SessionJoined -> {
                    // Show success message
                }
            }
        }
    }

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
                            text = stringResource(R.string.app_title),
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
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onIntent(HomeContract.Intent.AddSessionClicked) },
                containerColor = Primary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
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
                            text = stringResource(R.string.up_next),
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Black),
                            color = OnSurface
                        )
                        Text(
                            text = stringResource(R.string.up_next_subtitle),
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                            color = OnSurfaceVariant
                        )
                    }
                }

                items(state.sessions) { session ->
                    SessionCard(
                        session = session,
                        onJoinClick = { viewModel.onIntent(HomeContract.Intent.JoinSession(session.id)) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
fun SessionCard(
    session: Session,
    onJoinClick: () -> Unit
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
                        .background(if (session.isWeekday) SecondaryContainer else Primary)
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = session.type,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 10.sp,
                            letterSpacing = 1.sp
                        ),
                        color = if (session.isWeekday) OnSecondaryContainer else Color.White
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
                text = session.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = OnSurface
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Event, contentDescription = null, tint = Primary, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = session.date, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = OnSurfaceVariant)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = null, tint = Primary, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = session.location, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = OnSurfaceVariant)
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
                        text = stringResource(R.string.availability_label),
                        style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, letterSpacing = 1.sp),
                        color = Primary.copy(alpha = 0.6f)
                    )
                    Text(
                        text = stringResource(R.string.spots_left_format, session.spotsLeft),
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Black),
                        color = OnSurface
                    )
                }

                Button(
                    onClick = onJoinClick,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TertiaryFixed)
                ) {
                    Text(
                        text = stringResource(R.string.join_session),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = OnTertiaryFixed
                    )
                }
            }
        }
    }
}
