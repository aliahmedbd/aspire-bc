package com.example.aspirebc.domain.usecase

import com.example.aspirebc.domain.model.Member
import com.example.aspirebc.domain.repository.MemberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMemberByIdUseCase @Inject constructor(
    private val repository: MemberRepository
) {
    operator fun invoke(id: String): Flow<Member?> {
        return repository.getMemberById(id)
    }
}
