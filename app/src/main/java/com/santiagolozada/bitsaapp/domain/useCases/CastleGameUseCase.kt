package com.santiagolozada.bitsaapp.domain.useCases

import com.santiagolozada.bitsaapp.domain.entities.CastleModel
import com.santiagolozada.bitsaapp.domain.repository.GameRepository
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions

class CastleGameUseCase(private val gameRepository: GameRepository) {

    fun initialConditionWindows(status: WindowPositions) {
        gameRepository.initialConditionWindows(status)
    }

    fun getStateGame(): MutableList<CastleModel> =
        gameRepository.getCastleStatus()

    fun resetGame() {
        gameRepository.resetGame()
    }

    fun startingGame() {
        gameRepository.startingGame()
    }
}