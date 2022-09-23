package com.udevapp.neighbours.presentation.ui.fragments.main.templates.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.udevapp.data.api.user.UserResponse
import com.udevapp.neighbours.databinding.FragmentAddTemplatePerformersItemBinding

class TemplatePerformersAdapter(
    private var performers: List<UserResponse> = listOf(),
    var selectedPerformers: ArrayList<String> = ArrayList()
) : RecyclerView.Adapter<TemplatePerformersAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: FragmentAddTemplatePerformersItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val performer: MaterialCheckBox = binding.performerChecked
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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