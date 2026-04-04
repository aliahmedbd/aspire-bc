package com.example.aspirebc.ui.member

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.aspirebc.domain.model.Member
import com.example.aspirebc.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembersScreen(
    onBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    viewModel: MembersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

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
                            text = stringResource(R.string.club_directory),
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = OnSurfaceVariant,
                                letterSpacing = 2.sp
                            )
                        )
                        Text(
                            text = stringResource(R.string.active_members_title),
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 48.sp,
                                lineHeight = 44.sp,
                                color = Primary
                            )
                        )
                    }
                }

                item {
                    OutlinedTextField(
                        value = state.searchQuery,
                        onValueChange = { viewModel.onIntent(MembersContract.Intent.SearchMembers(it)) },
                        placeholder = { Text(stringResource(R.string.search_placeholder)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = OnSurfaceVariant) },
                        trailingIcon = {
                            IconButton(onClick = { /* Filter */ }, modifier = Modifier.background(Primary, RoundedCornerShape(8.dp))) {
                                Icon(Icons.Default.Tune, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SurfaceContainerHighest,
                            unfocusedContainerColor = SurfaceContainerHighest,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        )
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("ALL PLAYERS", "ELITE", "PRO", "ROOKIE").forEach { filter ->
                            val isSelected = state.selectedFilter == filter
                            Button(
                                onClick = { viewModel.onIntent(MembersContract.Intent.FilterMembers(filter)) },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isSelected) TertiaryFixed else SurfaceContainerLow,
                                    contentColor = if (isSelected) OnTertiaryFixed else OnSurfaceVariant
                                )
                            ) {
                                Text(text = filter, style = MaterialTheme.typography.labelMedium)
                            }
                        }
                    }
                }

                items(state.filteredMembers) { member ->
                    MemberItem(
                        member = member,
                        onClick = { onNavigateToDetail(member.id) }
                    )
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Primary)
                    ) {
                        Column(modifier = Modifier.padding(24.dp)) {
                            Text(
                                text = stringResource(R.string.elite_tip_label),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = Color.White.copy(alpha = 0.5f),
                                    letterSpacing = 2.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "\"Precision over power. The shuttlecock respects the angle.\"",
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium),
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(modifier = Modifier.width(32.dp).height(2.dp).background(TertiaryFixed))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = stringResource(R.string.coach_name),
                                    style = MaterialTheme.typography.labelMedium.copy(color = TertiaryFixed, letterSpacing = 1.sp)
                                )
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
fun MemberItem(
    member: Member,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(TertiaryFixed)
                        .border(2.dp, SurfaceContainerLow, CircleShape)
                        .align(Alignment.BottomEnd)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = member.name, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(Secondary))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = member.status, style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp), color = OnSurfaceVariant)
                }
            }
            IconButton(
                onClick = { /* Call */ },
                modifier = Modifier
                    .size(48.dp)
                    .background(PrimaryContainer, CircleShape)
            ) {
                Icon(imageVector = Icons.Default.Call, contentDescription = null, tint = Color.White)
            }
        }
    }
}
