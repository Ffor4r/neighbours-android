package com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet_dialog

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.RadioButton
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.neighbours.databinding.FragmentHomeBottomSheetItemBinding


class AddressRecyclerViewAdapter(
) :
    RecyclerView.Adapter<AddressRecyclerViewAdapter.ViewHolder>() {

    private var places: List<PlaceResponse> = listOf()
    private lateinit var editClickListener: OnItemClickListener

    var selectedPosition: Int = -1
    private var lastSelectedPosition: Int = selectedPosition

    interface OnItemClickListener {
        fun onItemClick(position: Int, placeResponse: PlaceResponse)
    }

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

    @SuppressLint("NotifyDataSetChanged")
    fun setPlaces(places: List<PlaceResponse>) {
        this.places = places
    }

    override fun onBindViewHolder(holder: AddressRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = places[position]
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
        holder.editButton.setOnClickListener{
            editClickListener.onItemClick(position, item)
        }
    }

    override fun getItemCount(): Int = places.size

    fun setEditOnClickListener(listener: OnItemClickListener) {editClickListener = listener}

    inner class ViewHolder(binding: FragmentHomeBottomSheetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val radioButton: RadioButton = binding.itemRadio
        val editButton: ImageButton = binding.itemEdit
    }
}