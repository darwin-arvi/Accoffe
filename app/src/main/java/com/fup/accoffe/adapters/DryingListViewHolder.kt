package com.fup.accoffe.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.databinding.ItemDryingListBinding
import com.fup.accoffe.models.DryingModel

class DryingListViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding= ItemDryingListBinding.bind(view)

    fun render(DryingModel: DryingModel,onClickInfo:(String)->Unit,onClickDelete:(String)->Unit,onClickEdit:(String)->Unit){
        binding.dryingId.text=DryingModel.id
        binding.dryingYear.text= DryingModel.d_year

        binding.btninfoDrying.setOnClickListener {
            onClickInfo.invoke(DryingModel.id!!)
        }
        binding.btnDeleteDrying.setOnClickListener {
            if (DryingModel.id  !=null){
                onClickDelete.invoke(DryingModel.id)
            }
        }
        binding.btnEditdrying.setOnClickListener {
            if (DryingModel.id  !=null){
                onClickEdit.invoke(DryingModel.id)
            }
        }
    }
}