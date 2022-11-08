package com.santiagolozada.bitsaapp.data.repository

import com.santiagolozada.bitsaapp.domain.entities.CastleModel
import com.santiagolozada.bitsaapp.domain.game.CastleManager
import com.santiagolozada.bitsaapp.domain.repository.GameRepository
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions

class GameRepositoryImpl(private val castleManager: CastleManager) : GameRepository {

    override fun initialConditionWindows(status: WindowPositions) {
        castleManager.initialConditionWindows(status)
    }

    override fun getCastleStatus(): MutableList<CastleModel> =
        castleManager.getConditionCastleWindows()

    override fun resetGame() {
        castleManager.initialConditionWindows(WindowPositions.OPEN)
    }

    override fun startingGame() {
        castleManager.startingGame()
    }

}