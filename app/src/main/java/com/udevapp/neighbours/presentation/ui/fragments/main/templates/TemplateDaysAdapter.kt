package com.udevapp.neighbours.presentation.ui.fragments.main.templates

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udevapp.data.pojo.CalendarDateModel
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentCalendarItemBinding
import com.udevapp.neighbours.databinding.FragmentTemplateItemDayItemBinding

class TemplateDaysAdapter(
    private val days: ArrayList<CalendarDateModel>,
    private val selectedDays: ArrayList<Int>
) : RecyclerView.Adapter<TemplateDaysAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FragmentTemplateItemDayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val calendarDay: TextView = binding.calendarDay
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TemplateDaysAdapter.ViewHolder {
        return ViewHolder(
            FragmentTemplateItemDayItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TemplateDaysAdapter.ViewHolder, position: Int) {
        val item = days[position]

        item.isSelected = selectedDays.contains(position + 1)

        if (item.isSelected) {
            holder.calendarDay.setBackgroundResource(R.drawable.calendar_day_highlight_bg)
        }

        holder.calendarDay.text = item.calendarDay
    }

    override fun getItemCount(): Int = days.size
}