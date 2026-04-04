package com.example.aspirebc.domain.usecase

import com.example.aspirebc.domain.model.User
import com.example.aspirebc.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(membershipType: String): Flow<Result<User>> {
        return repository.login(membershipType)
    }
}
