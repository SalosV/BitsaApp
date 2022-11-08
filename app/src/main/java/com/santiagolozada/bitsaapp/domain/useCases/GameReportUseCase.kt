package com.santiagolozada.bitsaapp.domain.useCases

import android.util.Log
import com.santiagolozada.bitsaapp.common.FIRST_VISITOR
import com.santiagolozada.bitsaapp.common.LAST_PLAYER
import com.santiagolozada.bitsaapp.domain.entities.ReportStateWindowsModel
import com.santiagolozada.bitsaapp.domain.repository.GameRepository
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions

class GameReportUseCase(private val gameRepository: GameRepository) {

    fun getReportGame(): ReportStateWindowsModel {
        val castle = gameRepository.getCastleStatus()
        val reportStateWindowsModel = ReportStateWindowsModel()
        castle.forEach { castleModel ->
            when (castleModel.windowPosition) {
                WindowPositions.CLOSED -> {
                    reportStateWindowsModel.numClosedWindow =
                        reportStateWindowsModel.numClosedWindow + 1
                }
                WindowPositions.OPEN -> {
                    reportStateWindowsModel.numOpenWindows =
                        reportStateWindowsModel.numOpenWindows + 1
                }
                WindowPositions.LEFT_OPEN -> {
                    reportStateWindowsModel.openLeftWings =
                        reportStateWindowsModel.openLeftWings + 1
                }
                WindowPositions.RIGHT_OPEN -> {
                    reportStateWindowsModel.openRightWings =
                        reportStateWindowsModel.openRightWings + 1
                }
                else -> {}
            }
        }

        return reportStateWindowsModel
    }

    fun getFirstWinners(): MutableList<Int> {
        val castle = gameRepository.getCastleStatus()
        val visitorsWinners = mutableListOf<Int>()

        castle.forEachIndexed { index, castleModel ->
            try {
                if (castleModel.idWindow == FIRST_VISITOR &&
                    castle[index + 1].windowPosition == WindowPositions.CLOSED &&
                    castleModel.windowPosition == WindowPositions.OPEN
                ) {
                    visitorsWinners.add(castleModel.idWindow)
                } else if (castleModel.idWindow == LAST_PLAYER &&
                    castle[index - 1].windowPosition == WindowPositions.CLOSED &&
                    castleModel.windowPosition == WindowPositions.OPEN
                ) {
                    visitorsWinners.add(castleModel.idWindow)
                } else if (castleModel.windowPosition == WindowPositions.OPEN &&
                    castle[index + 1].windowPosition == WindowPositions.CLOSED &&
                    castle[index - 1].windowPosition == WindowPositions.CLOSED
                ) {
                    visitorsWinners.add(castleModel.idWindow)
                }

            } catch (e: Exception) {
                Log.e("error", e.stackTraceToString())
            }
        }

        return visitorsWinners
    }

    fun getSecondWinners(): MutableList<Int> {
        val secondWinnerVisitors = mutableListOf<Int>()
        gameRepository.getCastleStatus().forEachIndexed { index, castleModel ->
            if (castleModel.windowPosition == WindowPositions.OPEN) {
                secondWinnerVisitors.add(castleModel.idWindow)
            }
        }
        return secondWinnerVisitors
    }
}