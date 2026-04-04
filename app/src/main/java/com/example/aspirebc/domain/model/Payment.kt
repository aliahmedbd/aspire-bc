package com.example.aspirebc.domain.model

data class Payment(
    val id: String,
    val amount: Double,
    val date: String,
    val status: String, // UNPAID, PAID
    val description: String
)

data class MemberPerformance(
    val progress: Float,
    val streak: String,
    val totalSaved: Double
)
