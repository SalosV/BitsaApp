package com.santiagolozada.bitsaapp.domain.repository

import com.santiagolozada.bitsaapp.domain.entities.CastleModel
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions

interface GameRepository {

    fun initialConditionWindows(status: WindowPositions)

    fun getCastleStatus(): MutableList<CastleModel>

    fun resetGame()

    fun startingGame()

}