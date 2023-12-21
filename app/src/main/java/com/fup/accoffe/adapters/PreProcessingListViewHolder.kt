package com.fup.accoffe.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.databinding.ItemPreProcessingListBinding
import com.fup.accoffe.models.PreProcessingModel

class PreProcessingListViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding= ItemPreProcessingListBinding.bind(view)

    fun render(PreProcessingModel: PreProcessingModel){
        binding.PreProcessingId.text= PreProcessingModel.id
        binding.PreProcessingYear.text=PreProcessingModel.b_year
    }
}