package com.example.aspirebc.domain.repository

import com.example.aspirebc.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun getSessions(): Flow<List<Session>>
    fun getSessionById(id: String): Flow<Session?>
    fun joinSession(sessionId: String): Flow<Result<Unit>>
    fun createSession(session: Session): Flow<Result<Unit>>
}
