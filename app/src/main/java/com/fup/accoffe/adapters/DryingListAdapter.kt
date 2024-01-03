package com.fup.accoffe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.R
import com.fup.accoffe.models.DryingModel

class DryingListAdapter (private val dryinglist:MutableList<DryingModel>,private val onClickDelete:(String)->Unit,private val onClickEdit:(String)->Unit): RecyclerView.Adapter<DryingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DryingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DryingListViewHolder(layoutInflater.inflate(R.layout.item_drying_list, parent, false))
    }

    override fun getItemCount(): Int = dryinglist.size

    override fun onBindViewHolder(holder: DryingListViewHolder, position: Int) {
        val item = dryinglist[position]
        holder.render(item,onClickDelete,onClickEdit)
    }
}