package com.fup.accoffe.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fup.accoffe.databinding.ItemEstateListBinding
import com.fup.accoffe.models.EstateModel

class EstateListViewHolder(view:View):ViewHolder(view) {
    private val binding= ItemEstateListBinding.bind(view)

    fun render(estateModel: EstateModel,onClickInfo:(String)->Unit){
        binding.estateId.text=estateModel.id
        binding.estateName.text=estateModel.ename
        binding.estateYear.text=estateModel.eyear

        binding.estateBtnInfo.setOnClickListener {
            if (estateModel.id  !=null){
            onClickInfo.invoke(estateModel.id)
        }
        }
    }


}