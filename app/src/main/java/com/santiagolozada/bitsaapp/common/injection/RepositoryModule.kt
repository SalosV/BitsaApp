package com.santiagolozada.bitsaapp.common.injection

import com.santiagolozada.bitsaapp.data.repository.GameRepositoryImpl
import com.santiagolozada.bitsaapp.domain.game.CastleManager
import com.santiagolozada.bitsaapp.domain.repository.GameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideGameRepository(castleManager: CastleManager): GameRepository =
        GameRepositoryImpl(castleManager)

}