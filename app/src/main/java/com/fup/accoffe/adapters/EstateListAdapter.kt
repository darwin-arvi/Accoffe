package com.fup.accoffe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.R
import com.fup.accoffe.models.EstateModel

class EstateListAdapter(private val estatelist:MutableList<EstateModel>,private val onClickDelete:(String)->Unit,private val onClickInfo:(String)->Unit,private val onClickEdit:(String)->Unit):RecyclerView.Adapter<EstateListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstateListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EstateListViewHolder(layoutInflater.inflate(R.layout.item_estate_list, parent, false))
    }

    override fun getItemCount(): Int = estatelist.size

    override fun onBindViewHolder(holder: EstateListViewHolder, position: Int) {
        val item = estatelist[position]
        holder.render(item,onClickDelete,onClickInfo, onClickEdit)
    }

}