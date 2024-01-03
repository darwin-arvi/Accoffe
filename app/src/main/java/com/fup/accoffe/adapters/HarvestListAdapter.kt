package com.fup.accoffe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.R
import com.fup.accoffe.models.HarvestModel

class HarvestListAdapter(private val harvetlist:MutableList<HarvestModel>,private val onClickInfo:(String)->Unit,private val onClickEdit:(String)->Unit,private val onClickDelete:(String)->Unit): RecyclerView.Adapter<HarvestListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarvestListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HarvestListViewHolder(layoutInflater.inflate(R.layout.item_harvest_list, parent, false))
    }

    override fun getItemCount(): Int = harvetlist.size

    override fun onBindViewHolder(holder: HarvestListViewHolder, position: Int) {
        val item = harvetlist[position]
        holder.render(item, onClickInfo,onClickEdit,onClickDelete)
    }
}