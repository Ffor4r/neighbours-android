package com.udevapp.neighbours.presentation.ui.fragments.main.templates

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentTemplateItemBinding
import com.udevapp.neighbours.presentation.utils.DaysGenerator

class TemplateAdapter(private val resources: Resources) :
    RecyclerView.Adapter<TemplateAdapter.ViewHolder>() {

    private var templates: MutableList<ScheduleTemplateResponse> = mutableListOf()

    private lateinit var editClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int, templateResponse: ScheduleTemplateResponse?)

        fun onDeleteItemClick(position: Int, templateResponse: ScheduleTemplateResponse?)
    }

    inner class ViewHolder(val binding: FragmentTemplateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val action: TextView = binding.action
        val performers: TextView = binding.performers
        val days: RecyclerView = binding.dayView
        val card: MaterialCardView = binding.templateItem
        val delete: ImageButton = binding.deleteItem
    }

    fun setTemplates(templates: List<ScheduleTemplateResponse>) {
        this.templates = templates.toMutableList()
        notifyDataSetChanged()
    }

    fun removeTemplate(position: Int, templateResponse: ScheduleTemplateResponse?) {
        templates.remove(templateResponse)
        notifyItemRemoved(position)
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
            holder.card.setOnClickListener {
                editClickListener.onItemClick(position, item)
            }
            holder.delete.setOnClickListener {
                editClickListener.onDeleteItemClick(position, item)
            }
        }
    }

    fun setEditOnClickListener(listener: OnItemClickListener) {
        editClickListener = listener
    }

    override fun getItemCount(): Int = templates.size
}