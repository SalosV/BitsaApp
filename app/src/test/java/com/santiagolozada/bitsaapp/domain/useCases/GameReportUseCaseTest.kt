package com.santiagolozada.bitsaapp.domain.useCases

import com.santiagolozada.bitsaapp.data.repository.GameRepositoryImpl
import com.santiagolozada.bitsaapp.domain.entities.CastleModel
import com.santiagolozada.bitsaapp.domain.game.CastleManager
import com.santiagolozada.bitsaapp.domain.repository.GameRepository
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


internal class GameReportUseCaseTest {

    private lateinit var gameRepository: GameRepository
    private val castleManager = CastleManager()
    private lateinit var castleState: MutableList<CastleModel>
    private lateinit var gameReportUseCase: GameReportUseCase

    @Before
    fun setUp() {
        gameRepository = GameRepositoryImpl(castleManager)
        gameReportUseCase = GameReportUseCase(gameRepository)
        castleManager.initialConditionWindows(WindowPositions.OPEN)
        castleManager.startingGame()
        castleState = castleManager.getConditionCastleWindows()
    }

    @Test
    fun getFirstWinners() {
        val firstWinner = gameReportUseCase.getFirstWinners()

        assertThat(firstWinner.count(), `is`(0))
    }

    @Test
    fun getSecondWinners() {
        val secondWinner = gameReportUseCase.getSecondWinners()

        assertThat(secondWinner.count(), `is`(2))
    }
}