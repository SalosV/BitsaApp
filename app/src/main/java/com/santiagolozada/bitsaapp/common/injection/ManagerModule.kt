package com.santiagolozada.bitsaapp.common.injection

import com.santiagolozada.bitsaapp.domain.game.CastleManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ManagerModule {

    @Provides
    @Singleton
    fun providerGameManager() = CastleManager()
}