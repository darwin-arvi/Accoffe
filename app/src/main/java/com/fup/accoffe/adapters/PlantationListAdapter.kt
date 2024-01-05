package com.fup.accoffe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.R
import com.fup.accoffe.models.PlantationModel

class PlantationListAdapter(private val plantationlist:MutableList<PlantationModel>,private val onClickDelete:(String)->Unit,private val onClickInfo:(String)->Unit,private val onClickEdit:(String)->Unit):RecyclerView.Adapter<PlantationListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantationListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlantationListViewHolder(layoutInflater.inflate(R.layout.item_plantation_list, parent, false))
    }

    override fun getItemCount(): Int = plantationlist.size

    override fun onBindViewHolder(holder: PlantationListViewHolder, position: Int) {
        val item = plantationlist[position]
        holder.render(item,onClickInfo,onClickDelete,onClickEdit)
    }
}