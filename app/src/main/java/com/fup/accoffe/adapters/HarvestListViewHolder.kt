package com.fup.accoffe.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.databinding.ItemEstateListBinding
import com.fup.accoffe.databinding.ItemHarvestListBinding
import com.fup.accoffe.models.HarvestModel

class HarvestListViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding= ItemHarvestListBinding.bind(view)

    fun render(HarvestModel: HarvestModel,onClickInfo:(String)->Unit,onClickEdit:(String)->Unit,onClickDelete:(String)->Unit){
        binding.harvestId.text=HarvestModel.id
        binding.harvestYear.text=HarvestModel.h_year

        binding.btninfo.setOnClickListener {
            onClickInfo.invoke(HarvestModel.id!!)
        }

        binding.btnDeleteHarvest.setOnClickListener {
            if (HarvestModel.id  !=null){
                onClickDelete.invoke(HarvestModel.id)
            }
        }

        binding.btnEditHarvest.setOnClickListener {
            if (HarvestModel.id  !=null){
                onClickEdit.invoke(HarvestModel.id)
            }
        }

    }
}