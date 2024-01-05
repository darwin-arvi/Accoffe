package com.fup.accoffe.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.databinding.ItemPreProcessingListBinding
import com.fup.accoffe.models.PreProcessingModel

class PreProcessingListViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding= ItemPreProcessingListBinding.bind(view)

    fun render(PreProcessingModel: PreProcessingModel,onClickInfo:(String)->Unit,onClickEdit:(String)->Unit,onClickDelete:(String)->Unit){
        binding.PreProcessingId.text= PreProcessingModel.id
        binding.PreProcessingYear.text=PreProcessingModel.b_year
        binding.btninfoPreProcessing.setOnClickListener {
            onClickInfo.invoke(PreProcessingModel.id!!)
        }
        binding.btnDeletePreProcessing.setOnClickListener {
            if (PreProcessingModel.id != null) {
                onClickDelete.invoke(PreProcessingModel.id)
            }
        }
        binding.btnEditPreProcessing.setOnClickListener {
            if (PreProcessingModel.id  !=null){
                onClickEdit.invoke(PreProcessingModel.id)
            }
        }

    }
}