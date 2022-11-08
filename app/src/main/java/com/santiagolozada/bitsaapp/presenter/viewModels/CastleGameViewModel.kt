package com.santiagolozada.bitsaapp.presenter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santiagolozada.bitsaapp.domain.entities.CastleModel
import com.santiagolozada.bitsaapp.domain.entities.ReportStateWindowsModel
import com.santiagolozada.bitsaapp.domain.useCases.CastleGameUseCase
import com.santiagolozada.bitsaapp.domain.useCases.GameReportUseCase
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CastleGameViewModel @Inject constructor(
    private val castleGameUseCase: CastleGameUseCase,
    private val gameReportUseCase: GameReportUseCase
) : ViewModel() {

    var castleStateLive = MutableLiveData<MutableList<CastleModel>>()
        private set

    var gameReportLive = MutableLiveData<ReportStateWindowsModel>()
        private set

    fun currentWindowsStatus(status: WindowPositions) {
        castleGameUseCase.initialConditionWindows(status)
        castleGameUseCase.startingGame()
        updateDataViewData()
    }

    fun resetGame() {
        castleGameUseCase.resetGame()
        updateDataViewData()
    }

    private fun updateDataViewData() {
        castleStateLive.value = castleGameUseCase.getStateGame()
        gameReportLive.value = gameReportUseCase.getReportGame()
    }

    fun playGame() {
        castleGameUseCase.startingGame()
        updateDataViewData()
    }

    fun getWinners(): Pair<MutableList<Int>, MutableList<Int>> {
        return Pair(gameReportUseCase.getFirstWinners(), gameReportUseCase.getSecondWinners())
    }

}