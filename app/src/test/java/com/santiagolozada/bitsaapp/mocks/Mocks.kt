package com.santiagolozada.bitsaapp.mocks

import com.santiagolozada.bitsaapp.common.NUM_VISITORS
import com.santiagolozada.bitsaapp.domain.entities.CastleModel
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions

object Mocks {

    val initialStateCastle: MutableList<CastleModel> = mutableListOf<CastleModel>().apply {
        repeat(NUM_VISITORS) {
            add(CastleModel(it + 1, WindowPositions.OPEN))
        }
    }

    val stateCastleAfterPlaying = mutableListOf(
        CastleModel(1, WindowPositions.OPEN), CastleModel(2, WindowPositions.OPEN)
    ).apply {
        repeat(62) {
            if ((it + 3) % 2 == 0) {
                add(CastleModel(it + 3, WindowPositions.RIGHT_OPEN))
            } else {
                add(CastleModel(it + 3, WindowPositions.LEFT_OPEN))
            }

        }
    }
}