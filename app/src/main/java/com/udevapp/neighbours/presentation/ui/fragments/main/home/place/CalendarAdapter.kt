package com.udevapp.neighbours.presentation.ui.fragments.main.home.place

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udevapp.data.pojo.CalendarDateModel
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentCalendarItemBinding

class CalendarAdapter(
    private val days: ArrayList<CalendarDateModel>,
    private val currentPosition: Int = 0,
    private var selectedPosition: Int = currentPosition,
    private var lastSelectedPosition: Int = selectedPosition
) :
    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FragmentCalendarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val calendarDay: TextView = binding.calendarDay
        val calendarDate: TextView = binding.calendarDate
        val item: LinearLayout = binding.calendarItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentCalendarItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = days[position]

        holder.calendarDay.text = item.calendarDay
        holder.calendarDate.text = item.calendarDate

        item.isSelected = position == selectedPosition

        holder.item.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = holder.adapterPosition
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (item.isSelected) {
            holder.item.setBackgroundResource(R.drawable.calendar_highlight_bg)
        } else {
            if (position == currentPosition) {
                holder.item.setBackgroundResource(R.drawable.calendar_current_bg)
            } else {
                holder.item.background = null
            }
        }


    }

    override fun getItemCount(): Int = days.size
}