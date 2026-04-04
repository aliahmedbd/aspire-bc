package com.example.aspirebc.domain.repository

import com.example.aspirebc.domain.model.MemberPerformance
import com.example.aspirebc.domain.model.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    fun getPayments(): Flow<List<Payment>>
    fun getMemberPerformance(): Flow<MemberPerformance>
    fun confirmPayment(paymentId: String): Flow<Result<Unit>>
}
