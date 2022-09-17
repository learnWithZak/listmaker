package com.zak.listmaker.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zak.listmaker.databinding.ListSelectionViewHolderBinding
import com.zak.listmaker.models.TaskList

class ListSelectionRecyclerViewAdapter(private val lists: MutableList<TaskList>): RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSelectionViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text = "${position + 1}"
        holder.binding.itemString.text = lists[position].name
    }

    override fun getItemCount(): Int = lists.size

    fun listsUpdated() {
        notifyItemInserted(lists.size - 1)
    }

}