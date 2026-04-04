package com.example.aspirebc.data.repository

import com.example.aspirebc.domain.model.Session
import com.example.aspirebc.domain.repository.SessionRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionRepositoryImpl @Inject constructor() : SessionRepository {
    private val mockSessions = mutableListOf(
        Session(
            id = "1",
            title = "Tuesday Evening Smash",
            type = "WEEKDAY SESSION",
            date = "Oct 24, 18:00 - 20:00",
            location = "Court 4",
            spotsLeft = 4,
            totalSpots = 16,
            isWeekday = true
        ),
        Session(
            id = "2",
            title = "Weekend Open Play",
            type = "WEEKEND SESSION",
            date = "Oct 28, 09:00 - 12:00",
            location = "Main Hall - Court 1 & 2",
            spotsLeft = 2,
            totalSpots = 20,
            isWeekday = false
        )
    )

    override fun getSessions(): Flow<List<Session>> = flow {
        delay(1000)
        emit(mockSessions)
    }

    override fun getSessionById(id: String): Flow<Session?> = flow {
        emit(mockSessions.find { it.id == id })
    }

    override fun joinSession(sessionId: String): Flow<Result<Unit>> = flow {
        delay(1000)
        val index = mockSessions.indexOfFirst { it.id == sessionId }
        if (index != -1 && mockSessions[index].spotsLeft > 0) {
            mockSessions[index] = mockSessions[index].copy(spotsLeft = mockSessions[index].spotsLeft - 1)
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Exception("No spots left")))
        }
    }

    override fun createSession(session: Session): Flow<Result<Unit>> = flow {
        delay(1000)
        mockSessions.add(session)
        emit(Result.success(Unit))
    }
}
