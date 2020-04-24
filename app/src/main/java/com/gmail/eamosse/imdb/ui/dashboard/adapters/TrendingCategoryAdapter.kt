package com.gmail.eamosse.imdb.ui.dashboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.imdb.databinding.CategoryListItemBinding

class TrendingCategoryAdapter(private val items: List<Category>) : RecyclerView.Adapter<TrendingCategoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: CategoryListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Category) {
            binding.item = item
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingCategoryAdapter.ViewHolder {
        val binding: CategoryListItemBinding = CategoryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TrendingCategoryAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

}