package com.example.aspirebc.domain.usecase

import com.example.aspirebc.domain.model.Session
import com.example.aspirebc.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSessionByIdUseCase @Inject constructor(
    private val repository: SessionRepository
) {
    operator fun invoke(sessionId: String): Flow<Session?> {
        return repository.getSessionById(sessionId)
    }
}
