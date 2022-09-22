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
import com.udevapp.neighbours.presentation.utils.DaysGenerator
import java.util.*

class TemplateAdapter(private val resources: Resources) :
    RecyclerView.Adapter<TemplateAdapter.ViewHolder>() {

    private var templates: List<ScheduleTemplateResponse> = listOf()

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
            holder.days.adapter = TemplateDaysAdapter(DaysGenerator().generate(), item.days)
            holder.performers.text =
                item.performers.joinToString { it.firstName }
                    .ifEmpty { resources.getString(R.string.for_all_template) }

        }
    }

    override fun getItemCount(): Int = templates.size
}