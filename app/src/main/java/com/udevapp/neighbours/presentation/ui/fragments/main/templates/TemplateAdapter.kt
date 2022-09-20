package com.udevapp.neighbours.presentation.ui.fragments.main.templates

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.data.pojo.CalendarDateModel
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentTemplateItemBinding
import java.util.*

class TemplateAdapter(private val resources: Resources) :
    RecyclerView.Adapter<TemplateAdapter.ViewHolder>() {

    private var templates: List<ScheduleTemplateResponse> = listOf()

    private val calendar = Calendar.getInstance(Locale.getDefault())

    inner class ViewHolder(val binding: FragmentTemplateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val action: TextView = binding.action
        val performers: TextView = binding.performers
        val days: RecyclerView = binding.dayView
    }

    fun setTemplates(templates: List<ScheduleTemplateResponse>) {
        this.templates = templates
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateAdapter.ViewHolder {
        return ViewHolder(
            FragmentTemplateItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TemplateAdapter.ViewHolder, position: Int) {
        if (templates.isNotEmpty()) {
            val item = templates[position]

            holder.action.text = item.action
            holder.days.adapter = TemplateDaysAdapter(setUpDays(), item.days)
            holder.performers.text =
                item.performers.joinToString { it.firstName }
                    .ifEmpty { resources.getString(R.string.for_all_template) }

        }
    }

    private fun setUpDays(): ArrayList<CalendarDateModel> {
        val dates = ArrayList<CalendarDateModel>()
        val globalCalendar = calendar.clone() as Calendar
        val maxDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_WEEK)
        dates.clear()
        globalCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        while (dates.size < maxDaysInMonth) {
            dates.add(CalendarDateModel(globalCalendar.time))
            globalCalendar.add(Calendar.DAY_OF_WEEK, 1)
        }

        return dates
    }

    override fun getItemCount(): Int = templates.size
}