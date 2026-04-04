package com.example.aspirebc.data.repository

import com.example.aspirebc.domain.model.MemberPerformance
import com.example.aspirebc.domain.model.Payment
import com.example.aspirebc.domain.repository.PaymentRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentRepositoryImpl @Inject constructor() : PaymentRepository {
    private val mockPayments = mutableListOf(
        Payment("1", 45.0, "Oct 2023", "UNPAID", "Membership Payment")
    )

    override fun getPayments(): Flow<List<Payment>> = flow {
        delay(800)
        emit(mockPayments)
    }

    override fun getMemberPerformance(): Flow<MemberPerformance> = flow {
        delay(500)
        emit(MemberPerformance(0.5f, "Elite Tier", 45.0))
    }

    override fun confirmPayment(paymentId: String): Flow<Result<Unit>> = flow {
        delay(1000)
        val index = mockPayments.indexOfFirst { it.id == paymentId }
        if (index != -1) {
            mockPayments[index] = mockPayments[index].copy(status = "PAID")
            emit(Result.success(Unit))
        } else {
            emit(Result.failure(Exception("Payment not found")))
        }
    }
}
