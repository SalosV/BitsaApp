package com.santiagolozada.bitsaapp.presenter.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiagolozada.bitsaapp.R
import com.santiagolozada.bitsaapp.databinding.ActivityMainBinding
import com.santiagolozada.bitsaapp.domain.entities.ReportStateWindowsModel
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions
import com.santiagolozada.bitsaapp.presenter.viewModels.CastleGameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val castleGameViewModel: CastleGameViewModel by viewModels()

    private val castleWindowsAdapter: CastleWindowsAdapter by lazy { CastleWindowsAdapter() }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservables()
        initRecycler()
        initListener()
        castleGameViewModel.currentWindowsStatus(WindowPositions.CLOSED)
    }

    private fun initListener() {
        binding.floatingReset.setOnClickListener { castleGameViewModel.resetGame() }
        binding.floatingStepByStep.setOnClickListener { castleGameViewModel.playGame() }
        binding.floatingWins.setOnClickListener { openWinnersDialog() }
    }

    private fun initObservables() {
        castleGameViewModel.castleStateLive.observe(this) { castle ->
            castle?.let { castleWindowsAdapter.castles = it.toList() }
        }
        castleGameViewModel.gameReportLive.observe(this, ::showReportGame)
    }

    private fun initRecycler() {
        binding.recyclerviewWindows.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewWindows.adapter = castleWindowsAdapter
    }

    private fun showReportGame(report: ReportStateWindowsModel) {
        binding.textViewOpenWindows.text =
            "${getString(R.string.open_windows)} ${report.numOpenWindows}"
        binding.textVIewClosedWindows.text =
            "${getString(R.string.closed_windows)} ${report.numClosedWindow}"
        binding.textViewOpenRightWindows.text =
            "${getString(R.string.open_right_windows)} ${report.openRightWings}"
        binding.textViewOpenLeftWindows.text =
            "${getString(R.string.open_left_windows)} ${report.openLeftWings}"
    }

    private fun openWinnersDialog() {
        val winnerDialog = WinnersDialog()
        winnerDialog.show(supportFragmentManager, "winnerDialog")
    }

}