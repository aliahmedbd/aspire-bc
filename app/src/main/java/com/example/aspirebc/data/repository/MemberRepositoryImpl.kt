package com.example.aspirebc.data.repository

import com.example.aspirebc.domain.model.Member
import com.example.aspirebc.domain.repository.MemberRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemberRepositoryImpl @Inject constructor() : MemberRepository {
    private val mockMembers = listOf(
        Member("1", "Ali Ahmed", "ELITE", "COURT 4 ACTIVE"),
        Member("2", "Sara Khan", "PRO", "COURT 2 ACTIVE"),
        Member("3", "John Doe", "ROOKIE", "IDLE"),
        Member("4", "Jane Smith", "ELITE", "COURT 1 ACTIVE"),
        Member("5", "Julian D.", "PRO", "COURT 4 ACTIVE")
    )

    override fun getMembers(): Flow<List<Member>> = flow {
        delay(1000)
        emit(mockMembers)
    }

    override fun searchMembers(query: String): Flow<List<Member>> = flow {
        delay(500)
        emit(mockMembers.filter { it.name.contains(query, ignoreCase = true) })
    }

    override fun getMemberById(id: String): Flow<Member?> = flow {
        delay(500)
        emit(mockMembers.find { it.id == id })
    }
}
