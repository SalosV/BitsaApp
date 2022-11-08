package com.santiagolozada.bitsaapp.domain.game

import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions.*

object WindowManager {

    fun changeWindowStatus(
        action: WindowPositions,
        windowStatus: WindowPositions
    ): WindowPositions {
        return when (action) {
            RIGHT_OPEN -> openRightDoor(windowStatus)
            LEFT_OPEN -> openLeftDoor(windowStatus)
            RIGHT_CLOSED -> closeRightDoor(windowStatus)
            else -> closeLeftDoor(windowStatus)
        }
    }

    private fun openRightDoor(status: WindowPositions): WindowPositions {
        return when (status) {
            RIGHT_OPEN -> RIGHT_OPEN
            LEFT_OPEN -> OPEN
            CLOSED -> RIGHT_OPEN
            else -> OPEN
        }
    }

    private fun openLeftDoor(status: WindowPositions): WindowPositions {
        return when (status) {
            RIGHT_OPEN -> OPEN
            LEFT_OPEN -> LEFT_OPEN
            CLOSED -> LEFT_OPEN
            else -> OPEN
        }
    }

    private fun closeRightDoor(status: WindowPositions): WindowPositions {
        return when (status) {
            LEFT_OPEN -> LEFT_OPEN
            RIGHT_OPEN -> CLOSED
            CLOSED -> CLOSED
            else -> LEFT_OPEN
        }
    }

    private fun closeLeftDoor(status: WindowPositions): WindowPositions {
        return when (status) {
            LEFT_OPEN -> CLOSED
            RIGHT_OPEN -> RIGHT_OPEN
            CLOSED -> CLOSED
            else -> RIGHT_OPEN
        }
    }

}