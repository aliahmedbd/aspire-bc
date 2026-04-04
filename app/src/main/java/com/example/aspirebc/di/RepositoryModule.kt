package com.example.aspirebc.di

import com.example.aspirebc.data.repository.AuthRepositoryImpl
import com.example.aspirebc.data.repository.MemberRepositoryImpl
import com.example.aspirebc.data.repository.PaymentRepositoryImpl
import com.example.aspirebc.data.repository.SessionRepositoryImpl
import com.example.aspirebc.domain.repository.AuthRepository
import com.example.aspirebc.domain.repository.MemberRepository
import com.example.aspirebc.domain.repository.PaymentRepository
import com.example.aspirebc.domain.repository.SessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindSessionRepository(
        sessionRepositoryImpl: SessionRepositoryImpl
    ): SessionRepository

    @Binds
    @Singleton
    abstract fun bindMemberRepository(
        memberRepositoryImpl: MemberRepositoryImpl
    ): MemberRepository

    @Binds
    @Singleton
    abstract fun bindPaymentRepository(
        paymentRepositoryImpl: PaymentRepositoryImpl
    ): PaymentRepository
}
