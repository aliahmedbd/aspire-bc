package com.example.aspirebc.domain.model

data class Member(
    val id: String,
    val name: String,
    val level: String, // ELITE, PRO, ROOKIE
    val status: String, // e.g., "COURT 4 ACTIVE"
    val avatarUrl: String? = null
)
