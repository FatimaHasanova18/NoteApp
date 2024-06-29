package com.example.notes

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.DayBinding

class DayAdapter(val dayList: ArrayList<DayofWeek>) : RecyclerView.Adapter<DayAdapter.DayHolder>() {

    class DayHolder(val binding: DayBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val binding = DayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DayHolder(binding)
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        val currentItem = dayList[position]
        holder.binding.weekname.text = currentItem.week
        holder.binding.daynumber.text = currentItem.day
        holder.binding.monthname.text = currentItem.month
        holder.itemView.setOnClickListener {
        }
    }
    override fun getItemCount(): Int {
        return dayList.size
    }
}
