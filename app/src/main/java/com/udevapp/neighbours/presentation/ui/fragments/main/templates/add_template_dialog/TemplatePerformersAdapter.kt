package com.udevapp.neighbours.presentation.ui.fragments.main.templates.add_template_dialog

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.api.user.UserResponse
import com.udevapp.data.pojo.CalendarDateModel
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentAddTemplateDayItemBinding
import com.udevapp.neighbours.databinding.FragmentAddTemplatePerformersItemBinding
import com.udevapp.neighbours.databinding.FragmentCalendarItemBinding
import com.udevapp.neighbours.databinding.FragmentTemplateItemDayItemBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet.AddressRecyclerViewAdapter

class TemplatePerformersAdapter(
    private var performers: List<UserResponse> = listOf(),
    val selectedPerformers: ArrayList<String> = ArrayList()
) : RecyclerView.Adapter<TemplatePerformersAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FragmentAddTemplatePerformersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val performer: MaterialCheckBox = binding.performerChecked
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TemplatePerformersAdapter.ViewHolder {
        return ViewHolder(
            FragmentAddTemplatePerformersItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPerformers(performers: List<UserResponse>) {
        this.performers = performers
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TemplatePerformersAdapter.ViewHolder, position: Int) {
        val item = performers[position]

        holder.performer.text = item.toString()
        holder.performer.isChecked = item.id in selectedPerformers
        holder.performer.setOnCheckedChangeListener { _, b ->
            if (b) {
                selectedPerformers.add(item.id)
            } else {
                selectedPerformers.remove(item.id)
            }
        }
    }


    override fun getItemCount(): Int = performers.size
}