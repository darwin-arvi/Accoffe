package com.fup.accoffe.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.databinding.ItemPlantationListBinding
import com.fup.accoffe.models.PlantationModel

class PlantationListViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding= ItemPlantationListBinding.bind(view)

    fun render(PlantationModel: PlantationModel){
        binding.plantationId.text=PlantationModel.id
        binding.plantationYear.text=PlantationModel.p_year
    }
}