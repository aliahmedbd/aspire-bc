package com.example.aspirebc.domain.repository

import com.example.aspirebc.domain.model.Member
import kotlinx.coroutines.flow.Flow

interface MemberRepository {
    fun getMembers(): Flow<List<Member>>
    fun searchMembers(query: String): Flow<List<Member>>
    fun getMemberById(id: String): Flow<Member?>
}
