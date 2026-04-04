package com.example.aspirebc.domain.repository

import com.example.aspirebc.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(membershipType: String): Flow<Result<User>>
    fun getCurrentUser(): Flow<User?>
}
