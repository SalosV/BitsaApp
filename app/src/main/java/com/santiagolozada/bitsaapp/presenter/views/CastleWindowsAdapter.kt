package com.santiagolozada.bitsaapp.presenter.views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.santiagolozada.bitsaapp.R
import com.santiagolozada.bitsaapp.databinding.CastleWindowItemBinding
import com.santiagolozada.bitsaapp.domain.entities.CastleModel
import com.santiagolozada.bitsaapp.presenter.utils.WindowPositions
import kotlin.properties.Delegates

class CastleWindowsAdapter : RecyclerView.Adapter<CastleWindowsAdapter.ViewHolder>() {

    var castles: List<CastleModel> by Delegates.observable(
        emptyList()
    ) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CastleWindowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = castles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val castle = castles[position]
        holder.bind(castle)
    }

    class ViewHolder(private val view: CastleWindowItemBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(castle: CastleModel) {
            view.textViewNumberWindow.text = castle.idWindow.toString()

            val (doorRight, doorLeft) = getColorPainWindow(castle.windowPosition)

            view.viewDoorLeft.setBackgroundColor(
                ContextCompat.getColor(
                    view.viewDoorLeft.context,
                    doorLeft
                )
            )

            view.viewDoorRight.setBackgroundColor(
                ContextCompat.getColor(
                    view.viewDoorRight.context,
                    doorRight
                )
            )
        }

        private fun getColorPainWindow(position: WindowPositions): Pair<Int, Int> {
            return when (position) {
                WindowPositions.RIGHT_OPEN -> Pair(R.color.open_window, R.color.closed_window)
                WindowPositions.LEFT_OPEN -> Pair(R.color.closed_window, R.color.open_window)
                WindowPositions.OPEN -> Pair(R.color.open_window, R.color.open_window)
                else -> Pair(R.color.closed_window, R.color.closed_window)
            }
        }
    }
}