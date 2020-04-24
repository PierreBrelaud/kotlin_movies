package com.gmail.eamosse.imdb.ui.dashboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.TrendingPerson
import com.gmail.eamosse.imdb.databinding.TrendingPersonItemBinding

class TrendingPersonAdapter(private val items: List<TrendingPerson>) : RecyclerView.Adapter<TrendingPersonAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: TrendingPersonItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TrendingPerson) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingPersonAdapter.ViewHolder {
        val binding: TrendingPersonItemBinding = TrendingPersonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TrendingPersonAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

}