package com.santiagolozada.bitsaapp.common.injection

import com.santiagolozada.bitsaapp.domain.repository.GameRepository
import com.santiagolozada.bitsaapp.domain.useCases.CastleGameUseCase
import com.santiagolozada.bitsaapp.domain.useCases.GameReportUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun providerStartGameUseCase(gameRepository: GameRepository) = CastleGameUseCase(gameRepository)

    @Provides
    fun providerReportGameUseCase(gameRepository: GameRepository) =
        GameReportUseCase(gameRepository)
}