package com.example.aspirebc.domain.usecase

import com.example.aspirebc.domain.model.Payment
import com.example.aspirebc.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPaymentsUseCase @Inject constructor(
    private val repository: PaymentRepository
) {
    operator fun invoke(): Flow<List<Payment>> {
        return repository.getPayments()
    }
}
