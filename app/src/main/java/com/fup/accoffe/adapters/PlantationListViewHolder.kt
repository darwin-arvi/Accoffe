package com.fup.accoffe.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.databinding.ItemPlantationListBinding
import com.fup.accoffe.models.PlantationModel

class PlantationListViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding= ItemPlantationListBinding.bind(view)

    fun render(plantationModel: PlantationModel,onClickDelete:(String)->Unit,onClickEdit:(String)->Unit){
        binding.plantationId.text=plantationModel.id
        binding.plantationYear.text=plantationModel.p_year

        binding.btnDeletePlantation.setOnClickListener {
            if (plantationModel.id  !=null){
                onClickDelete.invoke(plantationModel.id)
            }
    }
        binding.btnEditPlantation.setOnClickListener {
            if (plantationModel.id  !=null){
                onClickEdit.invoke(plantationModel.id)
            }
        }

}}