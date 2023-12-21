package com.fup.accoffe.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.databinding.ItemDryingListBinding
import com.fup.accoffe.models.DryingModel

class DryingListViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding= ItemDryingListBinding.bind(view)

    fun render(DryingModel: DryingModel){
        binding.dryingId.text=DryingModel.id
        binding.dryingYear.text= DryingModel.d_year
    }
}