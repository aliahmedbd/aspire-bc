package com.example.aspirebc.data.repository

import com.example.aspirebc.domain.model.User
import com.example.aspirebc.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor() : AuthRepository {
    override fun login(membershipType: String): Flow<Result<User>> = flow {
        delay(1500) // Simulate network delay
        val user = User(
            id = "1",
            name = "Ali Ahmed",
            email = "ali@example.com",
            membershipType = membershipType
        )
        emit(Result.success(user))
    }

    override fun getCurrentUser(): Flow<User?> = flow {
        // Mocking no logged in user initially
        emit(null)
    }
}
