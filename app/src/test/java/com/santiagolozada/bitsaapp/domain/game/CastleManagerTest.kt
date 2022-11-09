package com.santiagolozada.bitsaapp.domain.game

import com.santiagolozada.bitsaapp.domain.entities.CastleModel
import com.santiagolozada.bitsaapp.mocks.Mocks
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

internal class CastleManagerTest {

    private val castleManager = CastleManager()
    private lateinit var castleState: MutableList<CastleModel>

    @Before
    fun setUp() {
        castleManager.initialConditionWindows(WindowPositions.OPEN)
        castleState = castleManager.getConditionCastleWindows()
    }

    @Test
    fun `Validate the initial state of the castle`() {
        MatcherAssert.assertThat(castleState, `is`(Mocks.initialStateCastle))
    }

    @Test
    fun `Validate array after passing 64 players`() {
        castleManager.startingGame()
        castleState = castleManager.getConditionCastleWindows()
        MatcherAssert.assertThat(castleState, `is`(Mocks.stateCastleAfterPlaying))
    }
}