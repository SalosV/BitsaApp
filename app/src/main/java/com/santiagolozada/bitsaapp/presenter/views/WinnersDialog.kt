package com.santiagolozada.bitsaapp.presenter.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.santiagolozada.bitsaapp.databinding.WinnersDialogBinding
import com.santiagolozada.bitsaapp.presenter.viewModels.CastleGameViewModel


class WinnersDialog : DialogFragment() {

    private lateinit var binding: WinnersDialogBinding

    private lateinit var viewModel: CastleGameViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WinnersDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[CastleGameViewModel::class.java]

        val (firstWinner, secondWinner) = viewModel.getWinners()

        var firstWinnerText = ""
        var secondWinnerText = ""

        firstWinner.forEach { firstWinnerText += "$it, " }
        secondWinner.forEach { secondWinnerText += "$it, " }

        binding.textViewFirstWinnerList.text = firstWinnerText
        binding.textViewSecondWinnerList.text = secondWinnerText

        binding.buttonAccept.setOnClickListener { dismiss() }
    }
}