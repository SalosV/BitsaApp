package com.santiagolozada.bitsaapp.domain.game

import com.santiagolozada.bitsaapp.common.FIRST_VISITOR
import com.santiagolozada.bitsaapp.common.NUM_VISITORS
import com.santiagolozada.bitsaapp.common.SECOND_VISITOR
import com.santiagolozada.bitsaapp.domain.entities.CastleModel
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions
import javax.inject.Inject

class CastleManager @Inject constructor() {

    private lateinit var conditionCastleWindows: MutableList<CastleModel>

    fun initialConditionWindows(initialStateWindow: WindowPositions) {
        conditionCastleWindows = mutableListOf()

        (FIRST_VISITOR..NUM_VISITORS).forEachIndexed { _, visitor ->
            conditionCastleWindows.add(CastleModel(visitor, initialStateWindow))
        }
    }

    fun startingGame() {
        (FIRST_VISITOR..NUM_VISITORS).forEach { visitor ->
            conditionCastleWindows = rulesVisitors(visitor, conditionCastleWindows)
        }
    }

    private fun rulesVisitors(
        numVisitor: Int, conditionCastleWindows: MutableList<CastleModel>
    ): MutableList<CastleModel> {
        return when (numVisitor) {
            FIRST_VISITOR -> caseFirstPlayer(conditionCastleWindows)
            SECOND_VISITOR -> caseSecondPlayer(conditionCastleWindows)
            else -> caseForRestPlayer(numVisitor, conditionCastleWindows)
        }
    }

    private fun caseFirstPlayer(conditionCastleWindows: MutableList<CastleModel>): MutableList<CastleModel> {
        val listWindowsChange = conditionCastleWindows

        conditionCastleWindows.forEachIndexed { index, castleModel ->
            listWindowsChange[index].windowPosition = WindowManager.changeWindowStatus(
                WindowPositions.LEFT_OPEN, castleModel.windowPosition
            )
        }

        return listWindowsChange
    }

    private fun caseSecondPlayer(conditionCastleWindows: MutableList<CastleModel>): MutableList<CastleModel> {
        val listWindowsChange = conditionCastleWindows

        conditionCastleWindows.forEachIndexed { index, castleModel ->
            if (castleModel.idWindow % 2 == 0) {
                listWindowsChange[index].windowPosition = WindowManager.changeWindowStatus(
                    WindowPositions.RIGHT_OPEN, castleModel.windowPosition
                )
            }
        }

        return listWindowsChange
    }

    private fun caseForRestPlayer(
        numVisitor: Int, conditionCastleWindows: MutableList<CastleModel>
    ): MutableList<CastleModel> {
        val listWindowsChange = conditionCastleWindows

        conditionCastleWindows.forEachIndexed { index, castleModel ->
            val action = validCaseForRestPlayes(numVisitor, castleModel.idWindow)

            action?.let {
                listWindowsChange[index].windowPosition = WindowManager.changeWindowStatus(
                    it.first, castleModel.windowPosition
                )

                listWindowsChange[index].windowPosition = WindowManager.changeWindowStatus(
                    it.second, castleModel.windowPosition
                )
            }
        }

        return conditionCastleWindows
    }

    private fun validCaseForRestPlayes(
        numVisitor: Int, idWindow: Int
    ): Pair<WindowPositions, WindowPositions>? {
        return if (numVisitor % 2 == 0 && idWindow % numVisitor == 0) {
            Pair(WindowPositions.RIGHT_OPEN, WindowPositions.LEFT_CLOSED)
        } else if (idWindow % numVisitor == 0) {
            Pair(WindowPositions.LEFT_OPEN, WindowPositions.RIGHT_CLOSED)
        } else {
            null
        }
    }

    fun getConditionCastleWindows() = conditionCastleWindows

}
