package com.fup.accoffe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fup.accoffe.R
import com.fup.accoffe.models.PreProcessingModel

class PreProcessingListAdapter (private val preprocessinglist:MutableList<PreProcessingModel>): RecyclerView.Adapter<PreProcessingListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreProcessingListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PreProcessingListViewHolder(layoutInflater.inflate(R.layout.item_pre_processing_list, parent, false))
    }

    override fun getItemCount(): Int = preprocessinglist.size

    override fun onBindViewHolder(holder: PreProcessingListViewHolder, position: Int) {
        val item = preprocessinglist[position]
        holder.render(item)
    }
}