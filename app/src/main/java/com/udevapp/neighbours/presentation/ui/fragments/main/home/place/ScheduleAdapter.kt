package com.udevapp.neighbours.presentation.ui.fragments.main.home.place

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentScheduleItemBinding

class ScheduleAdapter(private val resources: Resources): RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    private var templates: MutableList<ScheduleTemplateResponse> = mutableListOf()

    inner class ViewHolder(val binding: FragmentScheduleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val action: TextView = binding.action
        val performers: TextView = binding.performers
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentScheduleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (templates.isNotEmpty()) {
            val item = templates[position]

            holder.action.text = item.action
            holder.performers.text =
                item.performers.joinToString { it.firstName }
                    .ifEmpty { resources.getString(R.string.for_all_template) }

        }
    }

    override fun getItemCount(): Int = templates.size

    fun setTemplates(templates: List<ScheduleTemplateResponse>) {
        this.templates = templates.toMutableList()
        notifyDataSetChanged()
    }
}