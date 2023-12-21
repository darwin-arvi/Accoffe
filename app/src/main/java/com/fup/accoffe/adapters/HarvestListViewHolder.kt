package com.fup.accoffe.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.databinding.ItemEstateListBinding
import com.fup.accoffe.databinding.ItemHarvestListBinding
import com.fup.accoffe.models.HarvestModel

class HarvestListViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding= ItemHarvestListBinding.bind(view)

    fun render(HarvestModel: HarvestModel){
        binding.harvestId.text=HarvestModel.id
        binding.harvestId.text=HarvestModel.h_year
    }
}