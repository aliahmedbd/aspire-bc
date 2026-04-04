package com.example.aspirebc.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aspirebc.R
import com.example.aspirebc.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onBack: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
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
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(PrimaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.user?.name?.take(2)?.uppercase() ?: "AU",
                            style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp),
                            color = Color.White
                        )
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
                            text = stringResource(R.string.elite_member_profile),
                            style = MaterialTheme.typography.labelMedium.copy(
                                color = OnSurfaceVariant,
                                letterSpacing = 2.sp
                            )
                        )
                        Text(
                            text = state.user?.name?.uppercase() ?: "",
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 48.sp,
                                lineHeight = 44.sp,
                                color = Primary
                            )
                        )
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLow)
                    ) {
                        Row(
                            modifier = Modifier.padding(24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(96.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(SurfaceContainerHigh)
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            Column {
                                Text(text = state.user?.name ?: "", style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp), color = Primary)
                                Text(text = stringResource(R.string.member_since_format, "2023"), style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = OnSurfaceVariant)
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Primary)
                                        .padding(horizontal = 12.dp, vertical = 2.dp)
                                ) {
                                    Text(text = state.user?.membershipType ?: "", style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp), color = Color.White)
                                }
                            }
                        }
                    }
                }

                item {
                    ProfileField(label = stringResource(R.string.mobile_number_label), value = "+971 50 123 4567", icon = Icons.Default.Smartphone)
                }

                item {
                    ProfileField(label = stringResource(R.string.home_address_label), value = "Downtown Burj District, Dubai, UAE", icon = Icons.Default.LocationOn)
                }

                item {
                    ProfileField(label = stringResource(R.string.emergency_contact_label), value = "Sara Ahmed (Spouse) • +971 55 987 6543", icon = Icons.Default.Emergency, isError = true)
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
fun ProfileField(label: String, value: String, icon: ImageVector, isError: Boolean = false) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceContainerLowest)
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isError) Error.copy(alpha = 0.1f) else SecondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = if (isError) Error else Primary)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = label, style = MaterialTheme.typography.labelMedium.copy(fontSize = 10.sp, letterSpacing = 1.sp), color = OnSurfaceVariant)
                Text(text = value, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = Primary)
            }
            Icon(imageVector = Icons.Default.Edit, contentDescription = null, tint = OutlineVariant)
        }
    }
}
