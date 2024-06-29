package com.example.notes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.TypeBinding

class TypeAdapter(private val typeList: ArrayList<TypeOfNote>) : RecyclerView.Adapter<TypeAdapter.TypeHolder>() {

    inner class TypeHolder(val binding: TypeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeHolder {
        val binding = TypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TypeHolder(binding)
    }

    override fun getItemCount(): Int {
        return typeList.size
    }

    override fun onBindViewHolder(holder: TypeHolder, position: Int) {
        val currentItem = typeList[position]
        holder.binding.Nov.text = currentItem.type
        val additionalWidthInDp = 22
        val additionalWidthInPx = additionalWidthInDp.dpToPx(holder.itemView.context)
        holder.binding.Nov.measure(0, 0)
        val textWidth = holder.binding.Nov.measuredWidth
        val layoutParams = holder.itemView.layoutParams
        layoutParams.width = textWidth + additionalWidthInPx
        holder.itemView.layoutParams = layoutParams

        holder.itemView.setOnClickListener {
        }
    }
    private fun Int.dpToPx(context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (this * density).toInt()
    }

}
