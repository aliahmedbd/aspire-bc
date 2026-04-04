package com.example.aspirebc.domain.usecase

import com.example.aspirebc.domain.model.User
import com.example.aspirebc.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(): Flow<User?> {
        return repository.getCurrentUser()
    }
}
