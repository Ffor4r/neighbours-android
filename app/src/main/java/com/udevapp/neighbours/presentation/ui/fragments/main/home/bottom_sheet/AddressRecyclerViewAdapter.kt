package com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.neighbours.databinding.FragmentHomeBottomSheetItemBinding


class AddressRecyclerViewAdapter(
    private val places: LiveData<List<PlaceResponse>>,
    private var selectedPosition: Int = 0,
    private var lastSelectedPosition: Int = selectedPosition
) :
    RecyclerView.Adapter<AddressRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AddressRecyclerViewAdapter.ViewHolder {

        return ViewHolder(
            FragmentHomeBottomSheetItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: AddressRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = places.value?.get(position)
        holder.radioButton.text = item.toString()
        holder.radioButton.isChecked = position == selectedPosition
        holder.radioButton.setOnCheckedChangeListener { _, b ->
            if (b) {
                lastSelectedPosition = selectedPosition
                selectedPosition = holder.adapterPosition
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (places.value == null) {
            0
        } else {
            places.value!!.size
        }
    }

    fun getSelectedPosition(): Int = selectedPosition

    inner class ViewHolder(binding: FragmentHomeBottomSheetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val radioButton: RadioButton = binding.itemRadio
        val editButton: ImageButton = binding.itemEdit
    }
}