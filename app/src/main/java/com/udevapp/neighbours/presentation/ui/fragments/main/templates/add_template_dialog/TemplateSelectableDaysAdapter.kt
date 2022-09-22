package com.udevapp.neighbours.presentation.ui.fragments.main.templates.add_template_dialog

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.pojo.CalendarDateModel
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentAddTemplateDayItemBinding
import com.udevapp.neighbours.databinding.FragmentCalendarItemBinding
import com.udevapp.neighbours.databinding.FragmentTemplateItemDayItemBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet.AddressRecyclerViewAdapter

class TemplateSelectableDaysAdapter(
    private val days: ArrayList<CalendarDateModel>,
    val selectedDays: ArrayList<Int> = ArrayList()
) : RecyclerView.Adapter<TemplateSelectableDaysAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FragmentAddTemplateDayItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val calendarDay: TextView = binding.calendarDay
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TemplateSelectableDaysAdapter.ViewHolder {
        return ViewHolder(
            FragmentAddTemplateDayItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TemplateSelectableDaysAdapter.ViewHolder, position: Int) {
        val item = days[position]

        item.isSelected = selectedDays.contains(position + 1)

        holder.calendarDay.text = item.calendarDay
        holder.calendarDay.setOnClickListener {
            if (item.isSelected) {
                selectedDays.remove(position + 1)
                notifyItemChanged(position)
            } else {
                selectedDays.add(position + 1)
                notifyItemChanged(position)
            }
        }

        if (item.isSelected) {
            holder.calendarDay.setBackgroundResource(R.drawable.calendar_day_highlight_bg)
        } else {
            holder.calendarDay.background = null
        }
    }

    override fun getItemCount(): Int = days.size
}